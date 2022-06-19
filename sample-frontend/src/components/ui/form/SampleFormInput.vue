<template>
  <label class="sample-form-input">
    <span class="label-text">{{ label }}</span>
    <input
        :type="type"
        class="form-input"
        :placeholder="placeholder"
        :value="modelValue"
        ref="inputRef"
        @input="handleInput"
        @focus="focused = true"
        @blur="focused = false"
    />
    <transition name="error" appear>
      <span class="error-message" v-if="validationError">TEST</span>
    </transition>
  </label>
</template>

<script lang="ts">
import { computed, defineComponent, PropType, ref } from "vue";

export default defineComponent({
  props: {
    label: String,
    placeholder: String,
    password: Boolean,
    modelValue: {} as any,
    rules: String,
  },
  emits: ['update:modelValue'],
  setup(props, context) {
    const focused = ref(false)
    const validationActive = ref(false)
    const type = computed(() => {
      return props.password ? 'password' : 'text'
    })

    const handleInput = (e: Event) => {
      validationActive.value = true
      const target = e.target as HTMLInputElement
      context.emit('update:modelValue', target.value)
    }

    const validator = new FormInputValidator()
    const validationError = computed(() => {
      if (validationActive.value && !focused.value) {
        return validator.validate(props.modelValue, ['required'])
      }
      return undefined
    })

    return {
      focused,
      type,
      handleInput,
      validationError,
    }
  }
})

type ValidatorRule = {
  [key: string]: {
    validate: { (value: any): boolean },
    errorCd: string
  }
}

class FormInputValidator {
  readonly rules: ValidatorRule = {
    'required': {
      validate: (value: any) => {
        console.log(value)
        if (typeof value === 'string') {
          return value.length > 0
        }
        return true
      },
      errorCd: 'required'
    },
  }

  validate(value: any, rules: Array<string>) {
    let error = undefined as string | undefined
    rules.forEach(rule => {
      if (!this.rules[`${rule}`].validate(value)) {
        error = this.rules[`${rule}`].errorCd
      }
    })
    return error
  }
}
</script>

<style lang="scss" scoped>
.sample-form-input {
  position: relative;
  margin-bottom: 10px;
  padding: 5px;
  display: inline-block;
  box-sizing: border-box;
  min-width: 180px;

  .form-input {
    padding: 0.5rem;
    width: 100%;
    font-size: 1rem;
    border: solid 1px #ccc;
    border-radius: 0.3rem;
    box-sizing: border-box;
    outline: none;

    &:focus {
      outline: none;
      border: solid 1px #fff;
      box-shadow: 0 0 5px 1px #81dcb3;

      transition: 0.3s;
    }
  }

  .label-text {
    font-weight: 600;
    text-align: left;
    display: block;
  }

  .error-message {
    display: block;
    position: absolute;
    color: #f63333;
  }
}

.error-leave-to,
.error-enter-from {
  opacity: 0;
  transform: translateY(-5px);
  transition: 0.3s;
}

.error-leave-active,
.error-enter-to {
  transition: 0.3s;
}
</style>