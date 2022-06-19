<template>
  <div class="sample-flex" :style="style">
    <slot />
  </div>
</template>

<script lang="ts">
import { computed, defineComponent } from "vue";

export default defineComponent({
  props: {
    justify: String,
    align: String,
  },
  setup(props) {
    const style = computed(() => {
      return {
        justifyContent: props.justify || 'center',
        alignItems: props.align || 'center'
      }
    })
    return {style}
  }
})
</script>

<style lang="scss" scoped>
.sample-flex {
  display: flex;
  height: 100%;
}
</style>