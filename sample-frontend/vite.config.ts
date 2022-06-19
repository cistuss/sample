import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import tsconfigPaths from 'vite-tsconfig-paths'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue(), tsconfigPaths()],
  resolve: {
    alias: {
      '@/': `${__dirname}/src/`,
    },
  },
  server: {
    proxy: {
      '^/api/*': {
        target: 'http://localhost:9000'
      }
    }
  }
})
