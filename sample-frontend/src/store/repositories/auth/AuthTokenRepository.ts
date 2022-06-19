import LocalStorageAdapter from "@/core/adapters/LocalStorageAdapter";

class AuthTokenRepository {
  private storageAdapter = new LocalStorageAdapter('auth-token')

  setToken(token: string) {
    this.storageAdapter.setData(token)
  }

  getToken() : string | null {
    return this.storageAdapter.getData()
  }

  removeToken() {
    this.storageAdapter.removeData()
  }
}

export default AuthTokenRepository