module.exports = {
  server: {
    port: 3000, // default: 3000
  },

  head: {
    title: '枝江心理',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      {
        hid: 'meta-key-words',
        name: 'keywords',
        content:
          '枝江心理官网_纽纽所上市企业，心理咨询及心理测评平台，关注您的身心健康，探究深层心理病因。',
      },
      {
        hid: 'description',
        name: 'description',
        content:
          '枝江心理官网_纽纽所上市企业，心理咨询及心理测评平台，关注您的身心健康，探究深层心理病因。',
      },
    ],
    link: [{ rel: 'icon', type: 'image/x-icon', href: '/favicon.png' }],
  },

  css: [
    // CSS file in the project
    '~/assets/css/common.css',
  ],

  modules: [
    '@nuxtjs/axios', //引入axios模块
  ],

  env: {
    BASE_API: 'http://localhost',
  },

  axios: {
    // Axios options here
    baseURL: 'http://localhost',
  },

  plugins: [
    '~/plugins/axios',
    '~/plugins/element-ui.js',
    '~/plugins/vue-qriously-plugin.js',
  ],

  // ssr: false, //设置为false表示客户端渲染，true为服务器端渲染，默认为true
}
