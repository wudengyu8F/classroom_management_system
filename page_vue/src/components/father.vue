<template>
  <div>
    <getRoom></getRoom>
    <insertInfor></insertInfor>
  </div>

</template>

<script>
  import getRoom from "./getRoom"
  import  insertInfor from "./insertInfor"
    export default {
        name: "father.vue",
      components: {
        getRoom: getRoom,
        insertInfor:insertInfor
      },
    }
</script>

<style scoped>
</style>
