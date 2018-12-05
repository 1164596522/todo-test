<template>
	
	<div style="float: left; width: 100%;">
		
		<div style="float: left; width: 45%;">
			<div style="margin: 0 auto; width: 25%;"><h1 style="color: #606266; margin: 0 auto;">添加列表</h1></div>
			<div style="margin: 20px 0;"></div>
			<span style="color:#909399">任务名称</span><div style="margin: 20px 0;"></div><el-input placeholder="请输入内容" suffix-icon="el-icon-date" v-model="todo.name"></el-input>
			<div style="margin: 20px 0;"></div>
			<span style="color:#909399">任务理由</span><div style="margin: 20px 0;"></div><el-input placeholder="请输入内容" suffix-icon="el-icon-date" v-model="todo.detail"></el-input>
  </el-input>
			<div style="margin: 20px 0;"></div>
			<span style="color:#909399">开始时间</span>
			<div style="margin: 20px 0;"></div>
			<div class="block" style="width: 100%;">
				<el-date-picker
					style="width: 100%;"
					v-model="todo.createdAt"
					type="datetime"
					placeholder="选择日期时间">
				</el-date-picker>
			</div>
			<div style="margin: 20px 0;"></div>
			<span style="color:#909399">结束时间</span>
			<div style="margin: 20px 0;"></div>
			<div class="block" style="width: 100%;">
				<el-date-picker
					style="width: 100%;"
					v-model="todo.finishedAt"
					type="datetime"
					placeholder="选择日期时间">
				</el-date-picker>
			</div>
			<div style="margin: 20px 0;"></div>
			<el-button type="primary" @click="addTodo">添加</el-button>
			<el-button @click="reset">重置</el-button>
		</div>
		
	

		
		<div style="float: right; width: 45%;">
			<div style="margin: 0 auto; width: 25%;"><h1 style="color: #606266; margin: 0 auto;">任务列表</h1></div>
			<div style="margin: 20px 0;"></div>
			<el-tabs type="border-card">
				
				<el-tab-pane label="所有任务">
					<div style="margin: 20px 0;" v-for="allTodo in allTodos" :key="allTodo">
						<el-card class="box-card" style="background-color: #C0C4CC;">
							<div slot="header" class="clearfix">
								<span>{{allTodo.name}}</span>
								<el-popover
									placement="right"
									width="700"
									trigger="click">
									<el-table :data="gridData">
										<el-table-column width="100" property="name" label="任务名称"></el-table-column>
										<el-table-column width="100" property="detail" label="任务理由"></el-table-column>
										<el-table-column width="200" property="createdAt" label="开始时间"></el-table-column>
										<el-table-column width="200" property="finishedAt" label="结束时间"></el-table-column>
										<el-table-column width="100" property="state" label="任务状态"></el-table-column>
									</el-table>
									<el-button style="float: right; padding: 3px 0" type="text" slot="reference" @click="viewDetails"><p style="display: none;">{{allTodo}}</p>查看详细</el-button>
								</el-popover>
								
							</div>
							<div>
								<span style="margin-left: 75%;">任务状态：{{allTodo.state}}</span>
							</div>
						</el-card>
						
					</div>
				</el-tab-pane>
				
				<el-tab-pane label="未开始的任务">
					<div style="margin: 20px 0;" v-for="unfinishedTodo in unfinishedTodos" :key="unfinishedTodo">
						<el-card class="box-card" style="background-color: #C0C4CC;">
						  <div slot="header" class="clearfix">
								<span>{{unfinishedTodo.name}}</span>
						    <el-popover
						    	placement="right"
						    	width="700"
						    	trigger="click">
						    	<el-table :data="gridData">
						    		<el-table-column width="100" property="name" label="任务名称"></el-table-column>
						    		<el-table-column width="100" property="detail" label="任务理由"></el-table-column>
						    		<el-table-column width="200" property="createdAt" label="开始时间"></el-table-column>
						    		<el-table-column width="200" property="finishedAt" label="结束时间"></el-table-column>
						    		<el-table-column width="100" property="state" label="任务状态"></el-table-column>
						    	</el-table>
						    	<el-button style="float: right; padding: 3px 0" type="text" slot="reference" @click="viewDetails"><p style="display: none;">{{unfinishedTodo}}</p>查看详细</el-button>
						    </el-popover>
						  </div>
							<div>
								<p style="display: none;">{{unfinishedTodo}}</p>
								<el-button type="primary" @click.native="modifyUnfinished">开始任务</el-button><span style="margin-left: 57%;">任务状态：{{unfinishedTodo.state}}</span>
							</div>
						</el-card>
						
					</div>
				</el-tab-pane>
				
				<el-tab-pane label="进行中的任务">
					<div style="margin: 20px 0;" v-for="ongoingTodo in ongoingTodos" :key="ongoingTodo">
						<p style="display: none;">{{ongoingTodo}}</p>
						<el-card class="box-card" style="background-color: #C0C4CC;">
						  <div slot="header" class="clearfix">
								<span>{{ongoingTodo.name}}</span>
						    <el-popover
						    	placement="right"
						    	width="700"
						    	trigger="click">
						    	<el-table :data="gridData">
						    		<el-table-column width="100" property="name" label="任务名称"></el-table-column>
						    		<el-table-column width="100" property="detail" label="任务理由"></el-table-column>
						    		<el-table-column width="200" property="createdAt" label="开始时间"></el-table-column>
						    		<el-table-column width="200" property="finishedAt" label="结束时间"></el-table-column>
						    		<el-table-column width="100" property="state" label="任务状态"></el-table-column>
						    	</el-table>
						    	<el-button style="float: right; padding: 3px 0" type="text" slot="reference" @click="viewDetails"><p style="display: none;">{{ongoingTodo}}</p>查看详细</el-button>
						    </el-popover>
						  </div>
							<div>
								<p style="display: none;">{{ongoingTodo}}</p>
								<el-button type="primary" @click="modifyCompleted">完成</el-button><el-button @click="deleteTodo">取消</el-button><span style="margin-left: 47%;">任务状态：{{ongoingTodo.state}}</span>
							</div>
						</el-card>
						
					</div>
				</el-tab-pane>
				
				<el-tab-pane label="已完成的任务">
					<div style="margin: 20px 0;" v-for="completedTodo in completedTodos" :key="completedTodo">
						<el-card class="box-card" style="background-color: #C0C4CC;">
						  <div slot="header" class="clearfix">
								<span>{{completedTodo.name}}</span>
						    <el-popover
						    	placement="right"
						    	width="700"
						    	trigger="click">
						    	<el-table :data="gridData">
						    		<el-table-column width="100" property="name" label="任务名称"></el-table-column>
						    		<el-table-column width="100" property="detail" label="任务理由"></el-table-column>
						    		<el-table-column width="200" property="createdAt" label="开始时间"></el-table-column>
						    		<el-table-column width="200" property="finishedAt" label="结束时间"></el-table-column>
						    		<el-table-column width="100" property="state" label="任务状态"></el-table-column>
						    	</el-table>
						    	<el-button style="float: right; padding: 3px 0" type="text" slot="reference" @click="viewDetails"><p style="display: none;">{{completedTodo}}</p>查看详细</el-button>
						    </el-popover>
						  </div>
							<div>
								<p style="display: none;">{{completedTodo}}</p>
								<el-button @click="deleteTodo">删除任务</el-button><span style="margin-left: 57%;">任务状态：{{completedTodo.state}}</span>
							</div>
						</el-card>
					</div>
				</el-tab-pane>
								
			</el-tabs>
		</div>
		
	</div>
	
</template>

<script>
	export default {
		name: 'app',
		data() {
				return {
						allTodos: [],
						unfinishedTodos:[],
						ongoingTodos:[],
						completedTodos:[],
						todo: {name: '', detail: '' ,createdAt:'',finishedAt:'',state:'未完成'},
						gridData: []
				}
		},
		methods:{
				allTasks(){
					fetch('http://localhost:9999/todo', null)
					.then(resp => resp.json())
					.then(data => {
							this.allTodos = data
					});
				},
				unfinishedTasks(){
					fetch('http://localhost:9999/todo/unfinished', null)
					.then(resp => resp.json())
					.then(data => {
							this.unfinishedTodos = data
					});
				},
				ongoingTasks(){
					fetch('http://localhost:9999/todo/ongoing', null)
					.then(resp => resp.json())
					.then(data => {
							this.ongoingTodos = data
					});
				},
				completedTasks(){
					fetch('http://localhost:9999/todo/completed', null)
					.then(resp => resp.json())
					.then(data => {
							this.completedTodos = data
					});
				},
				addTodo(){
					var obj = this;
					fetch('http://localhost:9999/todo',{
						method: 'POST',
						headers: {
							'Accept': 'application/json',
							'Content-Type': 'application/json'
						},
						body: JSON.stringify(this.todo)
					}).then(function(){
						obj.allTasks();
						obj.unfinishedTasks();
						obj.ongoingTasks();
						obj.completedTasks();
						obj.reset();
					});
				},
				modifyUnfinished(event){
					var obj = this;
					var upTodo = '';
					var upTodo1 = '';
					if(event.target.tagName == 'SPAN'){
						upTodo = JSON.parse(event.target.parentNode.parentNode.childNodes[0].innerHTML);
						upTodo1 = event.target.parentNode.parentNode.childNodes[0].innerHTML;
					}else if(event.target.tagName == 'BUTTON'){
						upTodo = JSON.parse(event.target.parentNode.childNodes[0].innerHTML);
						upTodo1 = event.target.parentNode.childNodes[0].innerHTML;
					}
					
					fetch('http://localhost:9999/todo/makeConduct',{
						method: 'PUT',
						headers: {
							'Accept': 'application/json',
							'Content-Type': 'application/json'
						},
						body: upTodo1
					}).then(function(){
						obj.allTasks();
						obj.unfinishedTasks();
						obj.ongoingTasks();
						obj.completedTasks();
						obj.ongoingTodos.push(upTodo);
					});
				},
				modifyCompleted(event){
					var obj = this;
					var upTodo = '';
					var upTodo1 = '';
					if(event.target.tagName == 'SPAN'){
						upTodo = JSON.parse(event.target.parentNode.parentNode.childNodes[0].innerHTML);
						upTodo1 = event.target.parentNode.parentNode.childNodes[0].innerHTML;
					}else if(event.target.tagName == 'BUTTON'){
						upTodo = JSON.parse(event.target.parentNode.childNodes[0].innerHTML);
						upTodo1 = event.target.parentNode.childNodes[0].innerHTML;
					}
					
					fetch('http://localhost:9999/todo/makeDone',{
						method: 'PUT',
						headers: {
							'Accept': 'application/json',
							'Content-Type': 'application/json'
						},
						body: upTodo1
					}).then(function(){
						obj.allTasks();
						obj.unfinishedTasks();
						obj.ongoingTasks();
						obj.completedTasks();
						obj.completedTodos.push(upTodo);
					});
				},
				deleteTodo(event){
					var obj = this;
					var upTodo = '';
					var upTodo1 = '';
					if(event.target.tagName == 'SPAN'){
						upTodo = JSON.parse(event.target.parentNode.parentNode.childNodes[0].innerHTML);
						upTodo1 = event.target.parentNode.parentNode.childNodes[0].innerHTML;
					}else if(event.target.tagName == 'BUTTON'){
						upTodo = JSON.parse(event.target.parentNode.childNodes[0].innerHTML);
						upTodo1 = event.target.parentNode.childNodes[0].innerHTML;
					}
					
					fetch('http://localhost:9999/todo',{
						method: 'DELETE',
						headers: {
							'Accept': 'application/json',
							'Content-Type': 'application/json'
						},
						body: upTodo1
					}).then(function(){
						obj.allTasks();
						obj.unfinishedTasks();
						obj.ongoingTasks();
						obj.completedTasks();
					});
				},
				reset(){
					this.todo.name = '';
					this.todo.detail = '';
					this.todo.createdAt = '';
					this.todo.finishedAt = '';
				},
				viewDetails(event){
						var upTodo = '';
						if(event.target.tagName == 'SPAN'){
							upTodo = event.target.childNodes[0].innerHTML;
						}else if(event.target.tagName == 'BUTTON'){
							upTodo = event.target.childNodes[2].childNodes[0].innerHTML;
						}
						
						fetch('http://localhost:9999/todo/viewdetails',{
							method: 'POST',
							headers: {
								'Accept': 'application/json',
								'Content-Type': 'application/json'
							},
							body: upTodo
						}).then(resp => resp.json())
							.then(data => {
								data[0].createdAt = this.timestampToTime(data[0].createdAt);
								data[0].finishedAt = this.timestampToTime(data[0].finishedAt);
								this.gridData = data;
						});
				},
				timestampToTime (date) {
					var date = new Date(date)
					var Y = date.getFullYear() + '-'
					var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-'
					var D = date.getDate() + ' '
					var h = date.getHours() + ':'
					var m = date.getMinutes() + ':'
					var s = date.getSeconds()
					return Y+M+D+h+m+s
				},
		},
		created (){
			this.allTasks();
			this.unfinishedTasks();
			this.ongoingTasks();
			this.completedTasks();
		}
	}
</script>
