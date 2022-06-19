<template>
  <div class="sample-nav-item" :class="{minimized: minimized, active: isActive}" @click="handleClick">
    <sample-image class="nav-item-icon" :src="iconSrc" />
    <div class="nav-item-label">
      <span>{{ label }}</span>
    </div>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent } from "vue";
import SampleImage from "@/components/ui/SampleImage.vue";
import SampleFlex from "@/components/ui/SampleFlex.vue";
import { useRoute, useRouter } from "vue-router";

export default defineComponent({
  components: {SampleFlex, SampleImage},
  props: {
    label: String,
    minimized: {
      type: Boolean,
      default: false
    },
    iconSrc: String,
    to: String
  },
  setup(props) {
    const router = useRouter()
    const handleClick = () => {
      if(props.to) {
        router.push({path: props.to})
      }
    }
    const route = useRoute()
    const isActive = computed(() => {
      if(props.to) {
        return route.path.startsWith(props.to)
      }
      return false
    })
    return {
      handleClick,
      isActive
    }
  }
})
</script>

<style lang="scss" scoped>
.sample-nav-item {
  margin: 5px 0;
  padding: 10px;
  position: relative;
  cursor: pointer;
  overflow: hidden;
  white-space: nowrap;

  .nav-item-icon {
    position: absolute;
    left: 18px;
    width: 22px;
    height: 22px;
    margin-left: 0;
    overflow: hidden;
    transition: 0.3s;
  }

  .nav-item-label {
    padding: 0;
    margin-left: 25%;
    font-weight: 700;
    font-size: 0.9rem;
    text-align: left;
    transition: 0.3s;
  }

  &:hover {
    .nav-item-label {
      color: #5fe1a8;
    }
    .nav-item-icon {
      background-color: #5fe1a8;
    }
  }
}

.sample-nav-item.minimized {
  .nav-item-label {
    opacity: 0;
    transition: 0.1s;
  }

  .nav-item-icon {
    left: 18px;
    margin: auto;
  }
}

.active {
  background-color: #ddd;
}
</style>