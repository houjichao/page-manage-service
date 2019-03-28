const admin = {
    template: `
    <el-container style="padding-top:20px;">
    <el-aside width="400px" style="border-radius: 2px;box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)">
        <el-table
            :data="userData"
            height="340"
            border
            style="width: 100%">
            <el-table-column
            label="序号"
            type=index
        width="100">
        </el-table-column>
        <el-table-column
        prop="name"
        label="姓名"
        width="150">
            </el-table-column>
            <el-table-column
                prop="address"
                label="操作">
                <template slot-scope="scope">
        		<el-button  type="primary" @click="deleteUser(scope.row.name)">删除</el-button>

    			</template>
            </el-table-column>
            </el-table>
    </el-aside>
    <el-main style="box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);border-radius: 2px;margin-left:20px"> 
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
             <el-form-item label="经度">
                <el-input v-model="formInline.E" placeholder="经度"></el-input>
            </el-form-item>
             <el-form-item label="纬度">
                <el-input v-model="formInline.N" placeholder="纬度"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">查询</el-button>
            </el-form-item>
        </el-form>
         <el-form :inline="true"  class="demo-form-inline">
            <el-form-item label="地区">
                <el-input v-model="address" placeholder="请输入地区"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onQueryAddress">查询</el-button>
            </el-form-item>
        </el-form>
        <el-table
    :data="tableData"
    height="380"
    border
    style="width: 100%" stripe highlight-current-row show-summary>
        <el-table-column
            prop="filename"
            label="文件名"
            width="250">
        </el-table-column>
        <el-table-column
            prop="address"
            label="地址"
            width="230">
        </el-table-column>
         <el-table-column
                prop="e"
            label="经度"
            width="110">
        </el-table-column>
         <el-table-column
                prop="n"
            label="纬度"
            width="110">
        </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button  type="primary" @click="deleteFile(scope.row.filename)">删除</el-button>

      </template>
    </el-table-column>
  </el-table>
    </el-main>
    </el-container>
    
    `,
    data: function () {
        return {
        	 formInline: {
                 E: '',
                 N: ''
             },
            tableData: [],
            userData:[],
            address:''
        }
    },
    methods: {
        onSubmit() {
        	  let temp = "E="+this.formInline.E.trim()+"&N="+this.formInline.N.trim()
        	  axios.post('/queryPhoto',temp)
        	  .then(res=>{
        		  this.tableData = res.data
        	  })
        },
        deleteFile(filename){
        	  let temp = "filename="+filename
        	  axios.post('/deleteFile',temp)
        	  .then(res=>{
        		  this.tableData = res.data
        	  })
        },
        deleteUser(name){
        	let temp = "username="+name
        	 axios.post('/deleteUser',temp)
       	  .then(res=>{
       		  this.userData = res.data
       	  })
        },
        onQueryAddress(){
        	if(this.address==''){
        		alert('请输入地址')
        	}else{
        		let temp = "address="+this.address
          	  axios.post('/queryPhotoByAddress',temp)
          	  .then(res=>{
          		  this.tableData = res.data
          	  })
        	}
        }
      },
      created:function() {
      	  // 获取照片数据
      	  axios.post('/findAllFile').then(res=>{
      		 this.tableData = res.data
      	  })
      	  
      	   axios.post('/user').then(res=>{
      		 this.userData = res.data
      	  })
        }
}