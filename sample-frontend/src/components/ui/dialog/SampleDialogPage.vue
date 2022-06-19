<template>
  <transition name="pane" appear v-on:after-leave="handleLeaveTransitionComplete">
    <div class="sample-pane" v-if="active">
      <div class="sample-pane-inner">
        <header class="sample-pane-header">
          <sample-flex class="sample-pane-logo">
            <h3>{{ headerLabel }}</h3>
          </sample-flex>
          <sample-flex class="pane-close-button-wrapper" @click="active = false">
            <sample-image class="pane-close-button" :size="16" src="/src/assets/images/sample-icon-cancel.png" />
          </sample-flex>
        </header>
        <main>
          <slot />
        </main>
      </div>
    </div>
  </transition>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import SampleFlex from "@/components/ui/SampleFlex.vue";
import SampleImage from "@/components/ui/SampleImage.vue";
import { useRouter } from "vue-router";

export default defineComponent({
  components: {SampleImage, SampleFlex},
  props: {
    headerLabel: String,
  },
  setup() {
    const active = ref(true)
    const router = useRouter()
    const handleLeaveTransitionComplete = () => { router.back() }

    return {
      active,
      handleLeaveTransitionComplete
    }
  }
})
</script>

<style lang="scss" scoped>
.sample-pane {
  padding: 0;
  position: fixed;
  top: 65px;
  right: 0;
  bottom: 0;
  width: 50%;
  min-width: 640px;
  box-shadow: -1px 0 10px 2px #ccc;

  .sample-pane-inner {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: white;

    .sample-pane-header {
      height: 48px;
      box-sizing: border-box;
      //box-shadow: 0 5px 5px 0 #ccc;
      border-bottom: solid 1px #ccc;

      .sample-pane-logo {
        width: 80%;
        height: 100%;
        margin: auto auto;

        h2 {
          margin: 0;
        }
      }

      .pane-close-button-wrapper {
        cursor: pointer;
        position: absolute;
        top: 0;
        right: 0;
        width: 48px;
        height: 48px;
        border-left: solid 1px #ccc;
        box-sizing: border-box;

        &:hover {
          .pane-close-button {
            transform: scale(1.2);
            transition: 0.3s;
          }
        }
      }
    }
  }
}

.closed {
  display: none;
}

.pane-leave-to,
.pane-enter-from {
  opacity: 0;
  transform: translateX(100%) !important;
  will-change: none;
  transition: 0.3s;
}

.pane-enter-active {
  will-change: transform;
  transition: transform 250ms;
}

.pane-leave-active {
  transition: 250ms;
}



</style>