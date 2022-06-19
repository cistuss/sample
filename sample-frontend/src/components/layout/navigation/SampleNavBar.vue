<template>
  <div class="sample-nav-bar" :class="{minimized: minimized}">
    <div class="sample-nav-bar-wrapper">
      <div class="sample-nav-items">
        <sample-nav-item
            label="閉じる"
            icon-src="/src/assets/images/sample-icon-menu.png"
            :minimized="minimized"
            @click="minimized = !minimized"
        />

        <sample-nav-item
            label="アカウント管理"
            to="/account"
            icon-src="/src/assets/images/sample-icon-member.png"
            :minimized="minimized"
        />

        <sample-nav-item
            label="テスト"
            to="/test"
            icon-src="/src/assets/images/sample-icon-test.png"
            :minimized="minimized"
        />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import SampleNavItem from "@/components/layout/navigation/SampleNavItem.vue";

export default defineComponent({
  components: {SampleNavItem},
  setup() {
    const minimized = ref(false)
    return {
      minimized
    }
  }
})
</script>

<style lang="scss" scoped>
.sample-nav-bar {
  position: relative;
  width: 180px;
  //height: 91vh;
  min-width: 180px;
  box-sizing: border-box;
  z-index: 98;
  transition: 0.3s;

  .sample-nav-bar-wrapper {
    position: fixed;
    left: 0;
    top: 65px;
    bottom: 0;
    width: 180px;
    background-color: #eee;
    transition: 0.3s;
  }
}

.sample-nav-bar.minimized {
  width: 64px;
  min-width: 64px;
  transition: 0.3s;

  .sample-nav-bar-wrapper {
    width: 64px;
    transition: 0.3s;
  }
}
</style>