export class EnumErrorMessage {
  static readonly values = [] as Array<EnumErrorMessage>
  static readonly COMMON_ERROR = new EnumErrorMessage('CMNERR00', 'システムエラーが発生しました')
  static readonly AUTH_FAILED = new EnumErrorMessage('AUTHFAIL', 'メールアドレスかパスワードが違います')


  readonly code: string
  readonly message: string

  private constructor(code: string, message: string) {
    this.code = code
    this.message = message
    EnumErrorMessage.values.push(this)
  }

  static codeOf(code: string): EnumErrorMessage | undefined {
    return EnumErrorMessage.values.find(e => e.code === code)
  }
}