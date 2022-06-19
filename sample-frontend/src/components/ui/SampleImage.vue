<template>
  <div class="sample-image" :style="style" />
</template>

<script lang="ts">
import { computed, defineComponent } from "vue";

export default defineComponent({
  props: {
    size: Number,
    width: Number,
    height: Number,
    src: {
      type: String,
      required: true
    }
  },
  setup(props) {
    const calcSize = (size?: number, width?: number, height?: number) => {
      if (size) {
        return {width: size + 'px', height: size + 'px'}
      }
      if (width && height) {
        return {width: width + 'px', height: height + 'px'}
      }
      if (width) {
        return {width: width + 'px', height: width + 'px'}
      }
      if (height) {
        return {width: height + 'px', height: height + 'px'}
      }
      return {width: '24px', height: '24px'}
    }

    const size = calcSize(props.size, props.width, props.height)
    const style = {
      width: size.width,
      height: size.height,
      backgroundImage: `url("${props.src}")`,
      maskImage: `url("${props.src}")`
    }
    return {
      style
    }
  }
})
</script>

<style lang="scss" scoped>
.sample-image {
  display: inline-block;
  min-width: 16px;
  min-height: 16px;

  background-color: #555;
  background-blend-mode: multiply;
  background-repeat: no-repeat;
  background-size: contain;
  -webkit-mask-repeat: no-repeat;
  -webkit-mask-size: contain;
}
</style>