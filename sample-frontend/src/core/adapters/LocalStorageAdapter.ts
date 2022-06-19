class LocalStorageAdapter {
  private storage: Storage = window.localStorage
  public storageKey: string

  constructor(key: string) {
    this.storageKey = key
  }

  public setData(data: string) {
    this.storage.setItem(this.storageKey, data)
  }

  public getData(): string | null {
    const data = this.storage.getItem(this.storageKey)
    if(data === null || data === undefined) {
      return null
    }
    return JSON.parse(JSON.stringify(data))
  }

  public removeData() {
    this.storage.removeItem(this.storageKey)
  }
}

export default LocalStorageAdapter