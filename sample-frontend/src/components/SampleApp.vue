<template>
  <div class="sample-app">
    <sample-dialog-toast />
    <sample-private-layout v-if="loggedIn">
      <slot />
    </sample-private-layout>
    <sample-public-layout v-else>
      <slot />
    </sample-public-layout>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, onErrorCaptured } from "vue";
import { useAuthModule } from "@/store/modules/auth/AuthModule";
import SamplePrivateLayout from "@/components/layout/SamplePrivateLayout.vue";
import SamplePublicLayout from "@/components/layout/SamplePublicLayout.vue";
import SampleDialogToast from "@/components/ui/dialog/SampleDialogToast.vue";

export default defineComponent({
  components: {SampleDialogToast, SamplePublicLayout, SamplePrivateLayout},
  setup() {
    const module = useAuthModule()
    const loggedIn = computed(() => {
      return module.loggedIn
    })

    return {
      loggedIn
    }
  }
})
</script>

<style lang="scss" scoped>
.sample-app {
  height: 100%;
}
</style>