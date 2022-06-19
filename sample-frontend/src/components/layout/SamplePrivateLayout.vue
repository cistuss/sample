<template>
  <div class="sample-layout-root">
    <sample-header/>
    <sample-flex class="sample-layout-flex" align="normal">
      <sample-nav-bar />
      <div class="sample-app-content">
        <slot />
      </div>
    </sample-flex>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import SampleHeader from "@/components/layout/header/SampleHeader.vue";
import SampleFlex from "@/components/ui/SampleFlex.vue";
import SampleNavBar from "@/components/layout/navigation/SampleNavBar.vue";

export default defineComponent({
  components: {SampleNavBar, SampleFlex, SampleHeader}
})
</script>

<style lang="scss" scoped>
.sample-layout-root {
  height: 100%;
  .sample-layout-flex {
    .sample-app-content {
      position: relative;
      width: 100%;
    }
  }
}
</style>