//package com.side.project.schedule;
//
//import com.side.project.api.HttpRequest;
//import com.side.project.model.ApartTrade;
//import com.side.project.model.Apartment;
//import com.side.project.model.Bubjungdong;
//import com.side.project.repository.ApartmentRepository;
//import com.side.project.repository.BubjungdongRepository;
//import com.side.project.repository.TradeListRepository;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.jackson.JsonObjectDeserializer;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static com.side.project.api.Url.*;
//import static com.side.project.api.Apikey.*;
//import static com.side.project.model.TradeType.*;
///**
// * 아파트 데이터 수집 스케줄러
// * 현재 수집중인 종목
// * 1.매매 2.
// */
//
//@Component
//public class ApartmentSchedule {
//
//    @Autowired
//    BubjungdongRepository bubjungdongRepository;
//
//    @Autowired
//    ApartmentRepository apartmentRepository;
//
//    @Autowired
//    TradeListRepository tradeListRepository;
//
//    private static List<Bubjungdong> bubjungdongs;
//    private static int num = 54;
//    private static LocalDate localDate = LocalDate.of(2015, 01, 1);
//    private static LocalDate ifLocalDate = LocalDate.of(2020, 8, 1);
//    private static HttpRequest httpRequest = new HttpRequest();
//    /**
//     * 아파트 매매 가격 데이터 수집
//     * 2015.01 ~ 2020.07 데이터 가져오기
//     * 법정동 코드 앞5자리 286개 * 67번 = 5148 > 총 21일 소요
//     */
//    @Scheduled(cron = "*/100 * * * * *") // 100초에 한번씩 수행
//    public void trade() throws Exception{
//        if(bubjungdongs == null){
//            bubjungdongs = bubjungdongRepository.findAll();
//            int temp = 0;
//            for (int i = 1; i < bubjungdongs.size(); i++) {
//                String a1 = String.valueOf(bubjungdongs.get(temp).getCode()).substring(0, 5);
//                String a2 = String.valueOf(bubjungdongs.get(i).getCode()).substring(0, 5);
//                if(a1.equals(a2)){
//                    bubjungdongs.remove(i);
//                    i--;
//                }else{
//                    temp = i;
//                }
//            }
//        }
//
//        System.out.println(bubjungdongs.size() + " 중 " + (num+1) + "번째");
//
//        if(ObjectUtils.isEmpty(bubjungdongs.get(num).getA2())){
//            num += 1;
//            return ;
//        }
//
//        String lawdcd = String.valueOf(bubjungdongs.get(num).getCode()).substring(0, 5);
//        StringBuilder dealymd = new StringBuilder();
//        if(localDate.getMonth().getValue() < 10){
//            dealymd.append(localDate.getYear()).append("0").append(localDate.getMonth().getValue());
//        }else{
//            dealymd.append(localDate.getYear()).append(localDate.getMonth().getValue());
//        }
//
//        System.out.println("LAWD_CD : " + lawdcd);
//        System.out.println("DEAL_YMD : " + dealymd.toString());
//
//        Map<String, String> parameter = new HashMap<>();
//
//        parameter.put("pageNo", "1"); // 고정
//        parameter.put("numOfRows", "10000"); // 고정
//        parameter.put("LAWD_CD", lawdcd); // 법정동 코드
//        parameter.put("DEAL_YMD", dealymd.toString()); // 날짜
//
//        String returnString = (String)httpRequest.publicDataRequest(APARTMENT_TRADE, PUBLIC_DATA_API_KEY, parameter);
//        System.out.println("return - " + returnString);
//
//        JSONParser parser = new JSONParser();
//        Object obj = parser.parse(returnString);
//
//        JSONObject jsonObj = (JSONObject) obj;
//        Object response = jsonObj.get("response");
//        System.out.println(response.toString());
//
//        JSONObject responseObj = (JSONObject) response;
//        Object body = responseObj.get("body");
//        System.out.println(body.toString());
//
//        JSONObject bodyObj = (JSONObject) body;
//        Object items = bodyObj.get("items");
//        System.out.println(items.toString());
//
//        Object totalCount = bodyObj.get("totalCount");
//        if((Long)totalCount == 0) {
//            if(localDate.isEqual(ifLocalDate)){
//                localDate = LocalDate.of(2015, 1, 1);
//                num += 1;
//                return ;
//            }
//            localDate = localDate.plusMonths(1);
//            return ;
//        }
//
//        JSONObject itemsObj = (JSONObject) items;
//        Object item = itemsObj.get("item");
//        System.out.println(item.toString());
//
//        JSONArray itemArray = (JSONArray)item;
//
//        // Apartment, TradeList db insert
//        for (int i = 0; i < itemArray.size(); i++) {
//            JSONObject it = (JSONObject)itemArray.get(i);
//
//            Apartment apartment = new Apartment();
//            apartment.setArchi(it.get("건축년도").toString().trim());
//            apartment.setCode(Long.valueOf(it.get("법정동시군구코드").toString().trim() + it.get("법정동읍면동코드").toString().trim()));
//            apartment.setName(it.get("아파트").toString().trim());
//
//            Apartment findApart = apartmentRepository.findApartmentByCodeAndNameAndArchi(apartment.getCode(), apartment.getName(), apartment.getArchi());
//            if(ObjectUtils.isEmpty(findApart)){
//                apartmentRepository.save(apartment);
//                findApart = apartmentRepository.findApartmentByCodeAndNameAndArchi(apartment.getCode(), apartment.getName(), apartment.getArchi());
//            }
//
//            ApartTrade apartTrade = new ApartTrade();
//            String tradeMoney = it.get("거래금액").toString().trim().replace(",","");
//            apartTrade.setTradeMoney(Integer.valueOf(tradeMoney));
//            LocalDate day = LocalDate.of(Integer.valueOf(it.get("년").toString().trim()),
//                    Integer.valueOf(it.get("월").toString().trim()),
//                    Integer.valueOf(it.get("일").toString().trim()));
//            apartTrade.setTradeDay(day);
//            apartTrade.setArea(Double.valueOf(it.get("전용면적").toString().trim()));
//            apartTrade.setFloor(Integer.valueOf(it.get("층").toString().trim()));
//            apartTrade.setTradeType(TRADE);
//            apartTrade.setApartment(findApart);
//
//            tradeListRepository.save(apartTrade);
//
//
//            System.out.println("거래금액 :" + it.get("거래금액").toString().trim());
//            System.out.println("건축년도 :" + it.get("건축년도"));
//            System.out.println("법정동시군구코드 :" + it.get("법정동시군구코드"));
//            System.out.println("법정동읍면동코드 :" + it.get("법정동읍면동코드"));
//            System.out.println(Long.valueOf(it.get("법정동시군구코드").toString().trim() + it.get("법정동읍면동코드").toString().trim()));
//            System.out.println("아파트 :" + it.get("아파트"));
//            System.out.println("년 :" + it.get("년"));
//            System.out.println("월 :" + it.get("월"));
//            System.out.println("일 :" + it.get("일"));
//            System.out.println("층 :" + it.get("층"));
//            System.out.println("전용면적 :" + it.get("전용면적"));
//        }
//
//        localDate = localDate.plusMonths(1);
//        if(localDate.isEqual(ifLocalDate)){
//            localDate = LocalDate.of(2015, 1, 1);
//            num += 1;
//        }
//    }
//}
