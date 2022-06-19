<template>
  <div class="sample-app-header">
    <div class="header-wrapper">
      <sample-flex class="header-content" justify="space-between">
        <sample-header-logo @click="$router.push({path: '/'})" />
        <sample-header-menu :account="accountName" />
      </sample-flex>
    </div>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, onMounted, ref } from "vue";
import SampleFlex from "@/components/ui/SampleFlex.vue";
import SampleHeaderLogo from "@/components/layout/header/SampleHeaderLogo.vue";
import SampleHeaderMenu from "@/components/layout/header/SampleHeaderMenu.vue";
import AccountRepository, { AccountResponse } from "@/store/repositories/account/AccountRepository";
import { useAuthModule } from "@/store/modules/auth/AuthModule";
import { useRouter } from "vue-router";

export default defineComponent({
  components: {
    SampleHeaderMenu,
    SampleHeaderLogo,
    SampleFlex
  },
  setup() {
    const account = ref<AccountResponse>()
    onMounted(async () => {
      try {
        const repository = new AccountRepository()
        const res = await repository.fetchMe()
        account.value = res.data
      } catch (e) {
        const module = useAuthModule()
        module.refreshToken()
        const router = useRouter()
        await router.push({path: '/login'})
      }
    })
    const accountName = computed(() => {
      return account.value?.lastName + ' ' + account.value?.firstName
    })
    return {
      account,
      accountName
    }
  }
})
</script>

<style lang="scss" scoped>
.sample-app-header {
  height: 65px;

  .header-wrapper {
    background-color: white;
    position: fixed;
    left: 0;
    right: 0;
    height: 65px;
    z-index: 99;
    box-shadow: 0 2px 5px 2px #ddd;

    .header-content {
      padding: 0 25px;
      height: 100%;

      .header-menu {
        //box-sizing: border-box;
        h2 {
          margin: 0;
        }

        &:hover {
          background: #ccc;
        }
      }
    }
  }
}
</style>