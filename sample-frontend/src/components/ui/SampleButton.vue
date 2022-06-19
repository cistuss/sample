<template>
  <button class="sample-button">
    {{ label }}
  </button>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  props: {
    label: String
  }
})
</script>

<style lang="scss" scoped>
.sample-button {
  padding: 0.8em 1.5em;
  min-width: 120px;
  cursor: pointer;
  color: white;
  background-color: #5fe1a8;
  font-weight: bold;
  border: none;
  border-radius: 0.5rem;
  outline: none;
}
</style>