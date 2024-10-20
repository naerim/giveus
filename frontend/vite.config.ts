import { defineConfig, loadEnv } from 'vite'
import react from '@vitejs/plugin-react'
import tsConfigPath from 'vite-tsconfig-paths'
import * as path from 'path'

// https://vitejs.dev/config/
export default (mode: string) => {
  process.env = { ...process.env, ...loadEnv(mode, process.cwd()) }
  return defineConfig({
    plugins: [react(), tsConfigPath()],
    resolve: {
      alias: {
        '@': path.resolve(__dirname, './src'), // '@'를 './src'로 매핑
      },
    },
  })
}
