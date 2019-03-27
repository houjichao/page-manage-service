const login ={
    template:`
    <el-container>
    <el-header height="150px" style=" display: flex;justify-content: center;align-items: center;"><h2>欢迎进入本科毕业论文管理系统/h2></el-header>
    <el-main style=" display: flex;justify-content: center;align-items: center;"> 
        <div style="width:300px">
            <el-input v-model="username" placeholder="请输入用户名"></el-input>
            <el-input v-model="passwd" type="password" placeholder="请输入密码" style="margin-top:20px"></el-input>
            <div style="width:300px;margin-top:20px">
            <el-radio v-model="role" label="user" border>普通用户</el-radio>
            <el-radio v-model="role" label="admin" border>系统管理员</el-radio>
    		<el-radio v-model="role" label="user" border>普通用户</el-radio>
            </div>
            <el-button type="primary"  style="width:300px;margin-top:20px" @click="login()">登录</el-button>
        </div>
    </el-main>
    <el-footer></el-footer>
    
    </el-container>
   
    
    `,
    data:function(){
        return {
            username:'',
            passwd:'',
            role:'user'
        }
    },
    methods: {
        login() {
         if(this.username!=''&&this.passwd!=''){
        	 let temp = "username="+this.username.trim()+"&password="+this.passwd+"&role="+this.role
       	  	axios.post('/login',temp)
       	  .then(res=>{
       		  if(res!=null&&res!=''){
       			 eventbus.$emit('loginupdate',res.data)
       			  router.replace({path:'/home'})
       		  }
       	  })
         }else{
        	 alert('请输入用户名和密码')
         }
        }
    }
}