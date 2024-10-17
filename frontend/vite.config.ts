import { defineConfig, loadEnv } from 'vite'
import react from '@vitejs/plugin-react'
import tsConfigPath from 'vite-tsconfig-paths'
import { VitePWA } from 'vite-plugin-pwa'
import * as path from 'path'
import { basename } from 'path'

// https://vitejs.dev/config/
export default (mode: string) => {
  process.env = { ...process.env, ...loadEnv(mode, process.cwd()) }
  return defineConfig({
    plugins: [
      react(),
      tsConfigPath(),
      VitePWA({
        registerType: 'autoUpdate',
        devOptions: { enabled: false },
        manifest: {
          name: 'GIVEUS',
          short_name: 'GIVEUS',
          display: 'standalone',
          orientation: 'portrait',
          start_url: '/',
          theme_color: '#ffffff',
          icons: [
            {
              src: 'icon/android-chrome-192x192.png',
              type: 'image/png',
              sizes: '192x192',
              purpose: 'any',
            },
            {
              src: 'icon/android-chrome-192x192.png',
              type: 'image/png',
              sizes: '192x192',
              purpose: 'maskable',
            },
            {
              src: 'icon/android-chrome-512x512.png',
              type: 'image/png',
              sizes: '512x512',
              purpose: 'any',
            },
            {
              src: 'icon/android-chrome-512x512.png',
              type: 'image/png',
              sizes: '512x512',
              purpose: 'maskable',
            },
          ],
        },
      }),
    ],
    resolve: {
      alias: {
        '@': path.resolve(__dirname, './src'), // '@'를 './src'로 매핑
      },
    },
    server: {
      port: 5173,
      host: 'localhost',
      origin: 'http://localhost:5173',
      hmr: {
        host: 'localhost',
      },
    },
  })
}
