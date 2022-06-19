<template>
  <div class="account-list-page">
    <div class="account-list-page-header">
      <h1>アカウント</h1>
    </div>
    <div class="account-list-wrapper">
      <sample-flex justify="right">
        <sample-button class="account-add-button" label="新規追加" @click="$router.push('/account/add')"/>
      </sample-flex>
      <table class="account-list-table">
        <tr class="table-header-row">
          <th>ID</th>
          <th>アカウント名</th>
          <th>メールアドレス</th>
          <th>ステータス</th>
          <th>登録日</th>
        </tr>
        <tr class="table-row" v-for="account in accounts">
          <td>{{ account.id }}</td>
          <td>{{ `${account.lastName} ${account.firstName}` }}</td>
          <td>{{ account.emailAddress }}</td>
          <td></td>
          <td><sample-formatted-date :date="account.registerDatetime" /></td>
        </tr>
      </table>
    </div>
    <router-view />
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from "vue";
import AccountRepository, { AccountResponse } from "@/store/repositories/account/AccountRepository";
import SampleFlex from "@/components/ui/SampleFlex.vue";
import SampleButton from "@/components/ui/SampleButton.vue";
import SampleFormattedDate from "@/components/ui/SampleDate.vue";

export default defineComponent({
  components: {SampleFormattedDate, SampleButton, SampleFlex},
  setup() {
    const accounts = ref<Array<AccountResponse>>([])
    onMounted(async () => {
      const repository = new AccountRepository()
      const response = await repository.fetchAll()
      accounts.value = response.data.items
    })
    return {
      accounts
    }
  }
})
</script>

<style lang="scss" scoped>
.account-list-page {
  .account-list-wrapper {
    max-width: 900px;
    margin: auto;

    .account-add-button {
      margin: 10px;
      width: 120px;
    }

    .account-list-table {
      width: 100%;
      min-width: 750px;
      overflow: hidden;
      margin: auto;
      border-collapse: collapse;

      .table-header-row {
        border-bottom: solid 1px #ddd;
      }

      .table-row {
        cursor: pointer;
        border-bottom: solid 1px #eee;

        &:hover {
          background-color: #eee;
        }
      }

      th, td {
        margin: 5px;
        padding: 8px;
        text-align: left;
      }
    }
  }

  .account-list-page-header {
    h1 {
      margin-bottom: 0;
    }
  }
}
</style>