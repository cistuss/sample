<template>
  <div class="sample-header-logo">
    <h1>Sample</h1>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({})
</script>

<style lang="scss" scoped>
.sample-header-logo {
  cursor: pointer;
  box-sizing: border-box;

  h1 {
    padding: 0 10px;
    margin: 0;
    background: linear-gradient(transparent 80%, #81dcb3 80%);
  }

  &:hover {
    transform: scale(1.05);
    transition: 0.3s;
  }
}
</style>