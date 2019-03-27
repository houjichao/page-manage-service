const home ={
    template:`
    <el-table
    :data="tableData"
    height="500"
    border
    style="width: 100%" stripe highlight-current-row show-summary>
     <el-table-column
    			type=index
                prop="id"
            label="序号"
            width="50">
        </el-table-column>
        <el-table-column
            prop="filename"
            label="文件名"
            width="250">
        </el-table-column>
        <el-table-column
            prop="takedate"
            label="拍摄日期"
            width="180">
        </el-table-column>
         <el-table-column
                prop="e"
            label="经度"
            width="120">
        </el-table-column>
         <el-table-column
                prop="n"
            label="纬度"
            width="120">
        </el-table-column>
        <el-table-column
                prop="address"
            label="地址"
            width="250">
        </el-table-column>
    <el-table-column
      prop="address"
      label="图片" width="200">
      <template slot-scope="scope">
      <img :src="'/downloadfile/'+scope.row.filename" style="width:200px;height:100px">
    </template>
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
  </el-table>
    `,
    data:function () {
        return {
            tableData:[],
            dialogVisible:false
        }
      },
      created:function() {
    	  // 获取照片数据
    	  axios.get('/findAllFile').then(res=>{
    		 console.log(res)
    		 this.tableData = res.data
    	  })
      },
      methods: {
    	  handleClose(done) {
    	        this.$confirm('确认关闭？')
    	          .then(_ => {
    	            done();
    	          })
    	          .catch(_ => {});
    	      }
        }
}