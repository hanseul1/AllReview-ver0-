<template>
  <v-app id="app">
    <v-app-bar
      app
      clipped-left
      :color="this.$store.state.color"
    >
      <v-app-bar-nav-icon @click="drawer = !drawer" />
      <span class="title ml-3 mr-5" @click="reloading">AllReview.com</span>
      <v-text-field
        solo-inverted
        flat
        hide-details
        label="Search"
        prepend-inner-icon="search"
      />
      <v-spacer />
      <v-btn v-if="user_id == null" text color="black" @click="login">Login</v-btn>
      <v-btn v-else text color="black" @click='logout'>Logout</v-btn>
      <v-btn v-if="user_id == null" text color="black" @click='signup'>Sign up</v-btn>
    </v-app-bar>

    <v-navigation-drawer
      v-model="drawer"
      app
      clipped
      color="grey lighten-4"
    >
      <v-list
        dense
        class="grey lighten-4"
      >
        <template v-for="(item, i) in items">
          <v-row
            v-if="item.heading"
            :key="i"
            align="center"
          >
            <v-col cols="6">
              <v-subheader v-if="item.heading">
                {{ item.heading }}
              </v-subheader>
            </v-col>
            <v-col
              cols="6"
              class="text-right"
            >
              <v-btn
                small
                text
              >edit</v-btn>
            </v-col>
          </v-row>
          <v-list-item
            v-else
            :key="i"
            link
            :to="item.link"
          >
            <v-list-item-action>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title class="grey--text">
                {{ item.text }}
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </template>
      </v-list>
    </v-navigation-drawer>

    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
import router from './router'
export default {
  name: 'App',
  props: {
    source: String
  },
  data: () => ({
    drawer: null,
    items: [
      {icon: 'post_add', text: 'Write Review', link: '/review/write'},
      {icon: 'assignment', text: '전체', link: '/review/list?category=all'},
      { icon: 'restaurant', text: '식품', link: '/review/list?category=food' },
      { icon: 'filter_vintage', text: '화장품/미용', link: '/review/list?category=beauty' },
      { icon: 'desktop_mac', text: '디지털/가전', link: '/review/list?category=digital' },
      { icon: 'how_to_reg', text: 'My Review', link: '/review/list?category=my' },
      { icon: 'account_circle', text: 'User Info', link: '/user/info' },
      { heading: 'Labels' },
      { icon: 'add', text: 'Create new label' },
      { divider: true },
      { icon: 'archive', text: 'Archive' },
      { icon: 'delete', text: 'Trash' },
      { divider: true },
      { icon: 'settings', text: 'Settings' },
      { icon: 'chat_bubble', text: 'Trash' },
      { icon: 'help', text: 'Help' }
    ],
    user_id: ''
  }),
  mounted () {
    this.user_id = this.$session.get('id')
    router.push('/')
  },
  methods: {
    login () {
      router.push('/user/login')
    },
    logout () {
      this.$session.destroy()
      window.location.reload()
    },
    signup () {
      router.push('/user/signup')
    },
    reloading () {
      router.push('/')
      window.location.reload()
    }
  }
}
</script>

<style>
#keep .v-navigation-drawer__border {
  display: none
}
</style>
