import { defineConfig, loadEnv } from 'vite'
import react from '@vitejs/plugin-react'
import tsConfigPath from 'vite-tsconfig-paths'
import { VitePWA } from 'vite-plugin-pwa'

// https://vitejs.dev/config/
export default (mode: string) => {
  process.env = { ...process.env, ...loadEnv(mode, process.cwd()) }
  return defineConfig({
    plugins: [
      react(),
      tsConfigPath(),
      VitePWA({
        registerType: 'autoUpdate',
        devOptions: { enabled: true },
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
    server: {
      port: 3000,
      host: true,
      origin: 'http://0.0.0.0',
      hmr: {
        host: 'localhost',
      },
    },
  })
}
