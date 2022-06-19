<template>
  <div class="login-page">
    <sample-flex>
      <div class="login-form">
        <form>
          <div class="login-form-header">
            <h1>Sample</h1>
          </div>
          <div class="form-input-wrapper">
            <label class="login-form-input">
              <span class="login-form-input-label">Email</span>
              <input type="email" v-model="form.email" placeholder="メールアドレス" />
            </label>
            <label class="login-form-input">
              <span class="login-form-input-label">Password</span>
              <input type="password" v-model="form.password" placeholder="パスワード" />
            </label>
          </div>
          <button class="login-form-submit-button" @click.prevent="handleSubmit">ログイン</button>
        </form>
      </div>
    </sample-flex>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { useAuthModule } from "@/store/modules/auth/AuthModule";
import { useRouter } from "vue-router";
import SampleFlex from "@/components/ui/SampleFlex.vue";

type LoginForm = {
  email: string,
  password: string,
}

export default defineComponent({
  components: {SampleFlex},
  setup() {
    const form = ref<LoginForm>({
      email: '',
      password: ''
    })

    const router = useRouter()

    const handleSubmit = async () => {
      const authModule = useAuthModule()
      await authModule.login({
        username: form.value.email,
        password: form.value.password
      })
      await router.push({path: '/test'})
    }

    return {
      form, handleSubmit
    }
  }
})
</script>

<style lang="scss" scoped>
.login-page {
  position: relative;
  padding: 30px;
  height: 80%;
  box-sizing: border-box;
  background-color: white;

  .login-form {
    width: 60%;
    min-width: 380px;
    max-width: 540px;
    box-sizing: border-box;
    margin: auto auto;
    padding: 10px;
    border-radius: 0.5rem;

    box-shadow: 0 0 16px 3px #ddd;

    .form-input-wrapper {
      width: 70%;
      margin: 0 auto 20px auto;
      padding: 5px;

      .login-form-input {
        display: block;
        margin: 10px 5px 0 5px;
        padding: 2px;

        .login-form-input-label {
          text-align: left;
          display: block;
        }

        input {
          padding: 10px;
          width: 100%;
          font-size: 1rem;
          box-sizing: border-box;
          border: solid 1px #ddd;
          border-radius: 0.3rem;

          &:focus {
            outline: none;
            border: solid 1px #fff;
            box-shadow: 0 0 5px 1px #81dcb3;

            transition: 0.3s;
          }
        }
      }
    }

    .login-form-header {
      h1 {
        width: 110px;
        padding: 0 10px;
        margin: 0 auto;
        background: linear-gradient(transparent 80%, #81dcb3 80%);
      }
    }

    .login-form-submit-button {
      cursor: pointer;
      padding: 10px 32px;
      //max-width: 30%;
      min-width: 180px;
      margin: 10px auto;
      background-color: #5fe1a8;
      border: none;
      border-radius: 0.2rem;
      font-size: 1rem;
      font-weight: bold;
      color: white;

      &:hover {
        transform: scale(1.05, 1.05);
        background-color: #4ac98e;
        transition: 0.3s;
      }
    }
  }
}
</style>