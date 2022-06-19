<template>
  <sample-dialog-page header-label="アカウント新規追加">
    <div class="account-add-page">
      <div class="account-add-form">
        <form>
          <div class="form-input-wrapper">
            <sample-flex class="account-info-account-name">
              <sample-form-input class="account-add-field half" label="姓" v-model="form.lastName"/>
              <sample-form-input class="account-add-field half" label="名" v-model="form.firstName" />
            </sample-flex>
            <sample-form-input class="account-add-field" label="メールアドレス" v-model="form.emailAddress"/>
          </div>
          <sample-button label="登録" @click.prevent="handleSubmit" />
        </form>
      </div>
    </div>
  </sample-dialog-page>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import SampleDialogPage from "@/components/ui/dialog/SampleDialogPage.vue";
import SampleFormInput from "@/components/ui/form/SampleFormInput.vue";
import SampleFlex from "@/components/ui/SampleFlex.vue";
import SampleButton from "@/components/ui/SampleButton.vue";
import AccountRepository, { AccountRegisterRequest } from "@/store/repositories/account/AccountRepository";
import { ToastType, useToastModule } from "@/store/modules/toast/ToastModule";

export type AccountAddForm = {
  firstName: string,
  lastName: string,
  emailAddress: string,
}

export default defineComponent({
  components: {
    SampleButton,
    SampleFlex,
    SampleFormInput,
    SampleDialogPage
  },

  setup() {
    const form = ref<AccountAddForm>({
      firstName: '',
      lastName: '',
      emailAddress: ''
    })

    const handleSubmit = () => {
      console.log(JSON.stringify(form.value))
      const repository = new AccountRepository()
      const request: AccountRegisterRequest = {
        emailAddress: form.value.emailAddress,
        firstName: form.value.firstName,
        lastName: form.value.lastName
      }
      repository.register(request)
    }
    return {
      form, handleSubmit
    }
  }
})
</script>

<style lang="scss" scoped>
.account-add-page {
  padding: 10px;

  .account-add-form {
    .form-input-wrapper {
      padding: 10px;

      .account-info-account-name {
        margin-bottom: 10px;
      }

      .account-add-field {
        width: 480px;

        &.half {
          width: 240px;
        }
      }
    }
  }

}
</style>