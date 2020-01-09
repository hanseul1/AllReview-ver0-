<template>
  <v-app id="app">
    <v-app-bar
      app
      clipped-left
      color="rgb(203, 203, 77)"
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
      <v-btn v-if="user_id == null" text color="black">Sign up</v-btn>
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
      <v-container
        fluid
        class="grey lighten-4 fill-height"
      >
      <router-view></router-view>
      </v-container>
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
      {icon: 'add_box', text: 'Write Review', link: '/writeReview'},
      { icon: 'restaurant', text: 'Food' },
      { icon: 'filter_vintage', text: 'Beauty' },
      { icon: 'desktop_mac', text: 'Electronic' },
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
      router.push('/login')
    },
    logout () {
      this.$session.destroy()
      window.location.reload()
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
