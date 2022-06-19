import { defineStore } from "pinia";
import AuthRepository, { AuthRequest } from "@/store/repositories/auth/AuthRepository";
import AuthTokenRepository from "@/store/repositories/auth/AuthTokenRepository";

export const useAuthModule = defineStore('auth', {
  state: () => ({
    authToken: null as string | null
  }),
  actions: {
    async login(request: AuthRequest) {
      const repository = new AuthRepository()
      const authResponse = await repository.login(request)
      if (authResponse.data.success) {
        this.authToken = authResponse.data.token || null
      }
    },

    async logout() {
      const repository = new AuthRepository()
      await repository.logout()
      new AuthTokenRepository().removeToken()
      this.authToken = null
    },

    refreshToken() {
      this.authToken = null
    }
  },
  getters: {
    loggedIn: (state) => {
      return state.authToken !== undefined && state.authToken !== null
    }
  },
  persist: {
    enabled: true,
    strategies: [
      {
        key: 'auth-token',
        storage: localStorage
      }
    ]
  }
})