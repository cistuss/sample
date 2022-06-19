import { defineStore } from "pinia";

export class ToastType {
  static readonly values = [] as Array<ToastType>
  static readonly INFO = new ToastType('info')
  static readonly ERROR = new ToastType('error')

  readonly code: string
  private constructor(code: string) {
    this.code = code
    ToastType.values.push(this)
  }
}

export type ToastMessage = {
  message: string,
  type: ToastType
}

export const useToastModule = defineStore('toast', {
    state: () => ({
      messages: [] as Array<ToastMessage>,
    }),

    actions: {
      add(message: ToastMessage) {
        this.messages.push(message)
      }
    }
  },
)
