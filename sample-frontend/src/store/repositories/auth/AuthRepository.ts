import BaseRepository from "@/store/repositories/BaseRepository";
import { AxiosPromise } from "axios";

export type AuthRequest = {
  username: string,
  password: string
}


export type AuthResponse = {
  success: boolean,
  token?: string
}

class AuthRepository extends BaseRepository {

  login(request: AuthRequest): AxiosPromise<AuthResponse> {
    return this.apiAdapter.post('/login', request)
  }

  logout() {
    this.apiAdapter.post('/logout', {})
  }
}

export default AuthRepository