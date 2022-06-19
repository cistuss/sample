import BaseRepository, { ApiListResponse } from "@/store/repositories/BaseRepository";
import { AxiosPromise } from "axios";

export type AccountResponse = {
  id: number,
  emailAddress: string,
  password: string,
  firstName: string | null,
  lastName: string | null,
  registerDatetime: Date | null,
  registerAccountId: number | null,
  updateDatetime: Date | null,
  updateAccountId: number | null
}

export type AccountRegisterRequest = {
  emailAddress: string,
  firstName: string,
  lastName: string
}

class AccountRepository extends BaseRepository {

  fetchMe(): AxiosPromise<AccountResponse> {
    return this.apiAdapter.get('/account/me', {})
  }

  fetchAll(): AxiosPromise<ApiListResponse<AccountResponse>> {
    return this.apiAdapter.get('/account/all', {})
  }

  register(request: AccountRegisterRequest) {
    this.apiAdapter.post('/account/register', request)
  }
}

export default AccountRepository
