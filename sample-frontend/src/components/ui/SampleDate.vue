<template>
  <span class="sample-date-display">{{ formatted }}</span>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import format from "date-fns/format"
import jaLocale from "date-fns/locale/ja"

export default defineComponent({
  props: {
    pattern: String,
    date: {
      type: Date,
      required: true
    }
  },
  setup(props) {
    const formatPattern = props.pattern || 'yyyy/MM/dd HH:mm:ss'
    const formatted = format(props.date, formatPattern, {locale: jaLocale})
    return {
      formatted
    }
  }
})
</script>

<style lang="scss" scoped>

</style>