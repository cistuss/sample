import ApiAdapter from "@/core/adapters/ApiAdapter";
import { AxiosRequestConfig, AxiosResponse } from "axios";
import { useAuthModule } from "@/store/modules/auth/AuthModule";

export type ApiListResponse<T> = {
  items: Array<T>
}

class BaseRepository {
  public apiAdapter = new ApiAdapter({
    requestInterceptors: [this.setAuthRequestHeader],
    responseInterceptors: [this.setAuthResponseHeader]
  })

  private setAuthRequestHeader(request: AxiosRequestConfig) {
    const module = useAuthModule()
    if(module.authToken && request.headers) {
      request.headers[`${import.meta.env.VITE_APP_X_AUTH_TOKEN_KEY}`] = module.authToken
    }
    return request
  }

  private async setAuthResponseHeader(response: AxiosResponse): Promise<AxiosResponse> {
    // adjust response
    if (response.status === 403) {
      const module = useAuthModule()
      module.refreshToken()
    }
    return response
  }
}


export default BaseRepository