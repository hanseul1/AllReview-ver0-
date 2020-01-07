<template>
    <v-card>
        <v-card-title class="grey darken-2">
          Login
        </v-card-title>
        <v-container>
          <v-row class="mx-2">
            <v-col
              class="align-center justify-space-between"
              cols="12"
            >
              <v-text-field
                prepend-icon="account_circle"
                placeholder="ID"
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                prepend-icon="lock"
                placeholder="Password"
              />
            </v-col>
          </v-row>
        </v-container>
        <v-card-actions>
          <v-spacer />
          <v-btn
            text
            color="primary"
            @click="dialog = false"
          >Login</v-btn>
        </v-card-actions>
      </v-card>
</template>

<script>
export default {
  name: 'Login'
}
</script>

<style>

</style>
