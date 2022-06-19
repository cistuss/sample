import { createApp } from 'vue'
import App from './App.vue'
import router from "./router/router";
import { createPinia } from "pinia";
import piniaPersist from 'pinia-plugin-persist'
import { HttpRequestError } from "@/core/models/errors/SampleExceptionTypes";
import { ToastType, useToastModule } from "@/store/modules/toast/ToastModule";
import { EnumErrorMessage } from "@/core/enums/EnumErrorMessage";

const pinia = createPinia()
pinia.use(piniaPersist)
const vue = createApp(App)
vue.use(router).use(pinia).mount('#app')
vue.config.errorHandler = (err, vm, info) => {
  console.log(err instanceof HttpRequestError)
  if(err instanceof HttpRequestError) {
    onHttpError(err)
  }
}
window.addEventListener("error", event => {
  console.log("Captured in error EventListener", event.error);
  // onHttpError(event.error)
});
window.addEventListener("unhandledrejection", event => {
  // console.log("Captured in unhandledrejection EventListener", event.reason);
  if (event.reason instanceof HttpRequestError) {
    onHttpError(event.reason)
  }
});

const onHttpError = (e: HttpRequestError) => {
  const toastModule = useToastModule()
  toastModule.add({
    message: EnumErrorMessage.codeOf(e.response.errorCd)?.message || 'システムエラーが発生しました',
    type: ToastType.ERROR
  })
}

