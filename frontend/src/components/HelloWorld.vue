<template>
  <div>
    <v-form>
      <v-container>
        <h1> 아파트 매매 리스트 </h1>
        <v-row>
          <v-col
            cols="12"
            md="3"
          >
            <v-text-field
              v-model="a1"
              :counter="10"
              label="ex) 서울, 서울특별시, 서울특 ..."
              required
            ></v-text-field>
          </v-col>

          <v-col
            cols="12"
            md="3"
          >
            <v-text-field
              v-model="a2"
              :counter="10"
              label="ex) 강남구, 강남 .."
              required
            ></v-text-field>
          </v-col>

          <v-col
            cols="12"
            md="3"
          >
            <v-text-field
              v-model="a3"
              :counter="10"
              label="동이름을 입력!"
              required
            ></v-text-field>
          </v-col>

          <v-col
            cols="12"
            md="3"
          >
            <v-text-field
              v-model="apartName"
              :counter="10"
              label="아파트 이름을 입력!"
              required
            ></v-text-field>
          </v-col>
        </v-row>
        <v-btn small @click="getApartTradeList">검색</v-btn>
      </v-container>
    </v-form>
    <v-container>
      <div v-for="(trades, index) in tradeList" :key="index">
        <div class="trade_list" v-for="(trade, index) in trades" :key="index">
          이름 : {{trade.apartment.name}}, 금액(만원) : {{trade.tradeMoney}}, 거래일시 : {{trade.tradeDay}}
          <br/>
          <br/>
        </div>
        <br/>
      </div>
    </v-container>
  </div>
</template>

<script>
import Server from "@/server/server.js"
export default {
  name: 'HelloWorld',
  data () {
    return {
      tradeList : [],
      a1 : '',
      a2 : '',
      a3 : '',
      apartName : ''
    }
  },
  created(){
    // this.test();
    
  },

  methods: {
    test(){
      Server("http://localhost:8080").get("/test").then(res => {
          console.log(res)
        }
      )
    },
    getApartTradeList(){
      Server("http://localhost:8080").get("/trade/list/" + this.a1 + "/" + this.a2 + "/" + this.a3 + "/" + this.apartName).then(res => {
          this.tradeList = res.data;
          console.log(this.tradeList)
        }
      )
    }
  }
}
</script>