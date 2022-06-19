<template>
  <transition name="toast" appear>
    <div class="sample-toast" v-if="active" @click="handleClick">
      <sample-flex>
        <div class="sample-toast-icon" :style="style">
          <sample-image :src="src" />
        </div>
        <div class="sample-toast-message">
          <sample-flex>
            <span>{{ messageText }}</span>
          </sample-flex>
        </div>
      </sample-flex>
      <div class="close-text">
        クリックで閉じる
      </div>
    </div>
  </transition>
</template>

<script lang="ts">
import { computed, defineComponent, ref } from "vue";
import SampleImage from "@/components/ui/SampleImage.vue";
import SampleFlex from "@/components/ui/SampleFlex.vue";
import { ToastType, useToastModule } from "@/store/modules/toast/ToastModule";

export default defineComponent({
  components: {SampleFlex, SampleImage},
  setup() {
    const src = ref('')
    const active = ref(false)
    const style = ref({})
    const messageText = ref<string>()
    const toastModule = useToastModule()
    const toastIntervalId = ref<number>()

    const polling = setInterval(() => {
      // polling toast message
      if (document.visibilityState !== 'visible') {
        return
      }
      if(active.value) {
        return false;
      }
      const message = toastModule.messages.shift()
      if (message) {
        const icon: { [key: string]: string; } = {
          'error': '/src/assets/images/sample-icon-cancel.png',
          'info': '/src/assets/images/sample-icon-info.svg'
        }
        src.value = icon[`${message.type.code}`]

        const color: { [key: string]: string; } = {
          'error': '#ff5665',
          'info': '#88cbfa'
        }
        style.value = {
          // border: `solid 1px ${color[message.type.code]}`,
          backgroundColor: color[message.type.code]
        }
        messageText.value = message.message

        active.value = true

        toastIntervalId.value = setInterval(() => {
          active.value = false
          clearInterval(toastIntervalId.value)
          toastIntervalId.value = undefined
        }, 2500)
      }

    }, 250)

    const handleClick = () => {
      clearInterval(toastIntervalId.value)
      toastIntervalId.value = undefined
      active.value = false
    }

    return {
      active,
      style,
      messageText,
      src,
      handleClick
    }
  }
})
</script>

<style lang="scss" scoped>
$border_color: #999;

.sample-toast {
  position: absolute;
  top: 80px;
  left: calc(50% - 180px);
  margin: auto;
  height: 48px;
  z-index: 100;

  .sample-toast-message {
    padding: 0 10px;
    z-index: 100;
    width: 360px;
    height: 48px;
    font-weight: 500;
    box-sizing: border-box;

    border: solid 1px $border_color;
    border-top-right-radius: 0.5rem;
    border-bottom-right-radius: 0.5rem;
    background-color: white;

    span {
      margin: auto;
      display: inline-block;
      width: 100%;
      text-align: left;
    }
  }

  .sample-toast-icon {
    width: 48px;
    height: 49px;
    box-sizing: border-box;
    //border: solid 1px #ccc;
    border-top: solid 1px $border_color;
    border-left: solid 1px $border_color;
    border-bottom: solid 1px $border_color;

    border-top-left-radius: 0.45rem;
    border-bottom-left-radius: 0.45rem;

    display: flex;
    justify-content: center;
    align-items: center;

    .sample-image {
      background-color: white;
    }
  }

  .toast-close-button {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 48px;
    height: 48px;
    border: solid 1px #555;
    border-top-right-radius: 0.45rem;
    border-bottom-right-radius: 0.45rem;
    box-sizing: border-box;

    .sample-image {
      background-color: white;
    }
  }

  .close-text {
    cursor: default;
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    opacity: 0;
    z-index: 101;

    border-radius: 0.5rem;
    box-sizing: border-box;

    display: flex;
    justify-content: center;
    align-items: center;
  }

  &:hover {
    .close-text {
      opacity: 1;
      //border: solid 1px #555;
      background-color: rgba(255, 255, 255, 0.5);
      transition: 0.5s;
    }

    .sample-toast-message {
      span {
        opacity: 0.2;
        transition: 0.3s;
      }
    }
  }
}


.toast-enter-from {
  transform: translateY(-20px);
  opacity: 0;
}

.toast-enter-to {
  transition: 0.3s;
}

.toast-enter-active {
}

.toast-leave-to {
  transform: translateY(50px);
  opacity: 0;
}

.toast-leave-active {
  transition: 150ms ease;
}

</style>