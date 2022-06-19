<script setup lang="ts">
// This starter template is using Vue 3 <script setup> SFCs
// Check out https://vuejs.org/api/sfc-script-setup.html#script-setup
import HelloWorld from './components/HelloWorld.vue'
import SampleApp from "@/components/SampleApp.vue";
</script>

<template>
  <sample-app>
    <router-view v-slot="{ Component }">
      <transition name="fade" appear>
        <component :is="Component" />
      </transition>
    </router-view>
  </sample-app>
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #555;
  height: 100%;
}

html {
  height: 100vh;

  body {
    height: 100%;
    margin: 0;
    padding: 0;
    overscroll-behavior-y: none;
  }
}

.fade-enter {
  opacity: 0;
}

.fade-enter-active {
  transition: opacity 0.5s;
  opacity: 0;
}

.fade-enter-to {
  opacity: 1;
}
</style>
