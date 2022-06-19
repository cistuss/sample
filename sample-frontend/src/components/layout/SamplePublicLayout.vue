<template>
  <div class="sample-layout-root">
    <slot />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({})
</script>

<style lang="scss" scoped>
.sample-layout-root {
  height: 100%;
}
</style>