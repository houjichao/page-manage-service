const queryAndUpload = {
    template: `
    <el-container style="padding:20px">
    <el-aside width="400px" style="border-radius: 2px;">
     <el-upload
        class="upload-demo"
        drag
        action="/uploadfile"
         multiple>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
      </el-upload>
    </el-aside>
    <el-main style="box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);border-radius: 2px;"> 
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
                prop="e"
            label="经度"
            width="110">
        </el-table-column>
         <el-table-column
                prop="n"
            label="纬度"
            width="110">
        </el-table-column>
          <el-table-column
                prop="address"
            label="地址"
            width="250">
        </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button  type="primary" @click="dialogVisible = true">查看大图</el-button>

    	<el-dialog
    	:title="scope.row.filename"
    	:visible.sync="dialogVisible"
    	width="80%"
    	:before-close="handleClose">
      <img :src="'/downloadfile/'+scope.row.filename" style="width:100%;height:100%">
    		<span slot="footer" class="dialog-footer">
    		<el-button type="primary" @click="dialogVisible = false">关闭</el-button>
    	</span>
    	</el-dialog>
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
            dialogVisible:false,
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
        },
        handleClose(done) {
	        this.$confirm('确认关闭？')
	          .then(_ => {
	            done();
	          })
	          .catch(_ => {});
	      }
    },
    created:function() {
  	  // 获取照片数据
  	  axios.post('/findAllFile').then(res=>{
  		 this.tableData = res.data
  	  })
    }
}