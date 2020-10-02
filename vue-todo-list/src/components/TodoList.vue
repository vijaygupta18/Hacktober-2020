<template>
  <div class="todo-list">
    <div class="input">
      <input type="text" v-model="input" />
      <button @click="addTask">Add</button>
    </div>
		<br>
		<hr>
    <ul>
      <li v-for="task in tasks" :key="task">{{ task }}</li>
    </ul>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";

@Options({})
export default class TodoList extends Vue {
  tasks: string[] = [];
  input = "";

  addTask() {
    if(!this.input) return;

    this.tasks.push(this.input);
    this.input = "";
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.todo-list {
  width: 200px;
	margin: 0px auto;
}
</style>
