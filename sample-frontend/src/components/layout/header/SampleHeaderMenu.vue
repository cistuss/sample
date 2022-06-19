<template>
  <div class="sample-header-menu">
    <sample-flex class="header-menu-wrapper" justify="right">
      <sample-flex>
        <span class="account-name">
          {{ account }}
        </span>
        <span class="menu-expander">＜</span>
      </sample-flex>

      <ul class="header-menu-dropdown">
        <li>
          <div class="header-menu-dropdown-item" @click="handleLogout">
            <span>ログアウト</span>
          </div>
          <div class="header-menu-dropdown-item">
            <span>テスト</span>
          </div>
        </li>
      </ul>
    </sample-flex>
  </div>
</template>

<script lang="ts">
import SampleFlex from "@/components/ui/SampleFlex.vue";
import { defineComponent } from "vue";
import { useAuthModule } from "@/store/modules/auth/AuthModule";
import { useRouter } from "vue-router";

export default defineComponent({
  components: {
    SampleFlex
  },
  props: {
    account: String
  },
  setup() {
    const router = useRouter()
    const module = useAuthModule()
    const handleLogout = async () => {
      await module.logout()
      await router.push({path: '/login'})
    }
    return {
      handleLogout
    }
  }
})
</script>

<style lang="scss" scoped>
.sample-header-menu {
  cursor: pointer;
  height: 100%;

  .header-menu-wrapper {
    position: relative;
    height: 100%;
    padding: 5px;
    box-sizing: border-box;
    min-width: 240px;

    transition: 0.3s;

    .header-menu-dropdown {
      list-style: none;
      display: block;
      background: white;
      position: absolute;
      right: 0;
      top: -20px;
      margin: 0;
      padding: 0;
      width: 100%;
      max-width: 240px;

      box-shadow: 3px 0 16px 1px #aaa;
      opacity: 0;

      .header-menu-dropdown-item {
        padding: 10px 0;

        &:hover {
          background-color: #eee;
        }
      }
    }

    &:hover {
      background-color: #eee;
      transition: 0.3s;

      .menu-expander {
        transform: rotateZ(-90deg);
        transition: 0.3s;
      }

      .header-menu-dropdown {
        //display: block;
        z-index: 1;
        top: 65px;
        opacity: 1;
        transition: opacity 1s, top 0.3s;
      }
    }

    .account-name {
      margin: auto auto;
      font-weight: 550;
      font-size: 1.1rem;
    }
    .menu-expander {
      display: inline-block;
      margin: auto 0 auto 10px;
      padding: 0 3px;
      font-weight: bold;
      font-size: 20px;
      transition: 0.3s;
    }
  }
}
</style>