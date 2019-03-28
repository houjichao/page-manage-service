const register ={
    template:`
    
     <el-container>
    <el-main style=" display: flex;justify-content: center;"> 
      <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">
    <el-form-item label="姓名" prop="name">
    <el-input type="name" v-model="ruleForm2.name" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item label="密码" prop="pass">
    <el-input type="password" v-model="ruleForm2.password" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item label="确认密码" prop="checkPass">
    <el-input type="password" v-model="ruleForm2.checkPass" autocomplete="off"></el-input>
  </el-form-item>
    <el-form-item label="邮箱" prop="email">
    <el-input v-model="ruleForm2.email"></el-input>
  </el-form-item>
  </el-form-item>
    <el-form-item label="手机号码" prop="phoneNo">
    <el-input v-model="ruleForm2.phoneNo"></el-input>
  </el-form-item>
    </el-form-item>
    <el-form-item label="证件号码" prop="idCardNumber">
    <el-input v-model="ruleForm2.idCardNumber"></el-input>
  </el-form-item>
  <el-form-item>
     <el-radio v-model="ruleForm2.userType" label="3" border>学生</el-radio>
            <el-radio v-model="ruleForm2.userType" label="2" border>老师</el-radio>
    		<el-radio v-model="ruleForm2.userType" label="1" border>系统管理员</el-radio>
  </el-form-item>
  
  
  
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm2')">提交</el-button>
    <el-button @click="resetForm('ruleForm2')">重置</el-button>
  </el-form-item>
</el-form>
    </el-main>
    <el-footer></el-footer>
    
    </el-container>
   
    `,
    data:function(){
    	
    	var checkObject = (rule,value,callback)=>{
    		  if (!value) {
                  return callback(new Error('姓名不能为空'));
               }else{
            	   callback();
               }
    	};
    	
    	var checkmail = (rule,value,callback)=>{
    		if(!value){
    			return callback(new Error('邮箱不能为空'));
    		}else{
    			let reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$")
    			if(!reg.test(value)){
    				return callback(new Error('邮箱格式不对'));
    			}
    			callback();
    			
    		}
    	};
    	
    	var checkIdNo = (rule,value,callback)=>{
    		if(!value){
    			return callback(new Error('身份证号不能为空'));
    		}else{
    			callback();
    			
    		}
    	}
    	
    	var checkPhoneNo = (rule,value,callback)=>{
    		 if (!value) {
                 return callback(new Error('电话号码不能为空'));
              }else{
           	   callback();
              }
    	};
    	var checkAge = (rule, value, callback) => {
            if (!value) {
              return callback(new Error('年龄不能为空'));
            }
            setTimeout(() => {
              if (!Number.isInteger(value)) {
                callback(new Error('请输入数字值'));
              } else {
                if (value < 18) {
                  callback(new Error('必须年满18岁'));
                } else {
                  callback();
                }
              }
            }, 1000);
          };
          var validatePass = (rule, value, callback) => {
            if (value === '') {
              callback(new Error('请输入密码'));
            } else {
              if (this.ruleForm2.checkPass !== '') {
                this.$refs.ruleForm2.validateField('checkPass');
              }
              callback();
            }
          };
          var validatePass2 = (rule, value, callback) => {
            if (value === '') {
              callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm2.password) {
              callback(new Error('两次输入密码不一致!'));
            } else {
              callback();
            }
          };
          return {
            ruleForm2: {
              name:'',
              password: '',
              checkPass: '',
              age: '',
              email:'',
              photoNo:'',
              idCardNumber:'',
              userType:'3'
            },
            rules2: {
              pass: [
                { validator: validatePass, trigger: 'blur' }
              ],
              checkPass: [
                { validator: validatePass2, trigger: 'blur' }
              ],
              age: [
                { validator: checkAge, trigger: 'blur' }
              ],
              name:[ {validator:checkObject, trigger: 'blur'}],
              email:[{validator:checkmail, trigger: 'blur'}],
              phoneNo:[{validator:checkPhoneNo, trigger: 'blur'}],
              idCardNumber:[{validator:checkIdNo, trigger: 'blur'}]
            }
          };
    },
    methods: {
    	submitForm(formName) {
            this.$refs[formName].validate((valid) => {
              if (valid) {
                alert('submit!');
              } else {
                console.log('error submit!!');
                return false;
              }
            });
          },
          resetForm(formName) {
            this.$refs[formName].resetFields();
          }
        
    }
}