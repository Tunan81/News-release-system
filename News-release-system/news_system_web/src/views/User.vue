<template>
  <div>
    <div style="padding: 10px 0">
      <el-input style="width: 200px;" placeholder="请输入姓名" suffix-icon="el-icon-search" class="ml-5"
                v-model="username"></el-input>
      <el-input style="width: 200px;" placeholder="学历" suffix-icon="el-icon-document" class="ml-20"
                v-model="degree"></el-input>
      <el-input style="width: 200px;" placeholder="角色" suffix-icon="el-icon-user
" class="ml-20" v-model="role">
      </el-input>
      <el-button class="ml-20" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-20" type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd" class="ml-5">新增<i class="el-icon-circle-plus-outline"></i>
      </el-button>
      <el-popconfirm
          class="ml-5"
          confirmButtonText='确定'
          cancelButtonText='不用了'
          icon="el-icon-info"
          iconColor="red"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch">
        <el-button type="danger" slot="reference" class="ml-10">批量删除<i class="el-icon-remove-outline"></i>
        </el-button>
      </el-popconfirm>

      <el-upload :action="'http://localhost:9090/user/import'" :show-file-list="false" accept="xlsx"
                 :on-success="handleExcelImportSuccess" style="display: inline-block">
        <el-button type="primary" class="ml-10">导入 <i class="el-icon-bottom"></i></el-button>
      </el-upload>
      <el-button type="primary" class="ml-10" @click="exp">导出<i class="el-icon-upload2"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55px">
      </el-table-column>
      <el-table-column prop="userId" label="编号"></el-table-column>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="sex" label="性别"></el-table-column>
      <el-table-column prop="hobbit" label="爱好"></el-table-column>
      <el-table-column prop="degree" label="学历"></el-table-column>
      <el-table-column prop="role" label="角色"></el-table-column>
      <el-table-column prop="status" label="状态">
        <template slot-scope="scope">
          <p>启用：
            <el-switch
                :active-value="1"
                :inactive-value="0"
                v-model="scope.row.status"
                @change="updateState(scope.row)">
            </el-switch>
          </p>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="380" aligin="center">
        <temple slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">查看详情</el-button>
          <el-button type="warning" @click="handleResetPassword(scope.row)">重置密码</el-button>
          <el-popconfirm
              class="ml-10"
              confirmButtonText='确定'
              cancelButtonText='不用了'
              icon="el-icon-info"
              iconColor="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.userId)">
            <el-button type="danger" slot="reference">删除用户<i class="el-icon-delete"></i></el-button>
          </el-popconfirm>
        </temple>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="用户详细信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <template>
            <el-radio v-model="form.sex" label="男">男</el-radio>
            <el-radio v-model="form.sex" label="女">女</el-radio>
          </template>
        </el-form-item>
        <el-form-item label="爱好">
          <el-input v-model="form.hobbit" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="学历">
          <template>
            <el-radio v-model="form.degree" label="高中">高中</el-radio>
            <el-radio v-model="form.degree" label="大专">大专</el-radio>
            <el-radio v-model="form.degree" label="本科">本科</el-radio>
            <el-radio v-model="form.degree" label="研究生">研究生</el-radio>
          </template>
        </el-form-item>
        <el-form-item label="角色">
            <el-select clearable v-model="form.role" placeholder="请选择角色">
              <el-option v-for="item in roles" :key="item.name" :label="item.name" :value="item.flag">
              </el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" v-model="form.note"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 定义修改密码确认框弹窗 -->
    <el-dialog :visible.sync="resetDialogVisible" title="重置密码" width="30%">
      <span>您确定要重置用户 {{ this.form.username }} 的密码吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="resetDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="resetPassword">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "User",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      username: "",
      userId: "",
      sex: "",
      hobbit: "",
      degree: "",
      role:"",
      roles: [],
      status: "",
      note:"",
      form: {},
      multipleSelection: [],
      resetRule: "",
      dialogFormVisible: false,
      resetDialogVisible: false, // 是否显示确认框弹窗
      updateStateDialogVisible: false, // 是否显示修改状态弹窗
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          degree:this.degree,
          role:this.role,
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
      this.request.get("/role").then(res => {
        this.roles = res.data
      })
    },
    save() {
      this.request.post("/user", this.form).then(res => {
        if (res) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    // 点击重置密码按钮时触发
    handleResetPassword(row) {
      this.form = row
      this.resetDialogVisible = true;
    },
    // 点击确认框的确定按钮时触发
    resetPassword() {
      this.request.post('/user/resetPassword',this.form).then(res => {
        if (res){
          this.$message.success("重置成功")
          this.resetDialogVisible = false
        }else {
          this.$message.error("重置失败")
        }
      })
    },
    updateState(row){
      this.form = row
      this.request.post("/user/updateState", this.form).then(res => {
        if (res) {
          this.$message.success("修改成功")
          this.load()
        } else {
          this.$message.error("修改失败")
        }
      })
    },
    handleUpdateRule(row){
      this.form = row
      this.updateStateDialogVisible = true
    },
    //点击确认框的确定按钮时触发
    resetRules(){
      this.request.post('/user/resetRules',this.form).then(res => {
        if (res){
          this.$message.success("修改成功")
          this.updateStateDialogVisible = false
        }else {
          this.$message.error("修改失败")
        }
      })
    },
    updataState(row) {
      this.form = row
      this.request.post("/user/modifiedState", this.form).then(res => {
        if (res) {
          this.$message.success("修改成功")
          this.load()
        } else {
          this.$message.error("修改失败")
        }
      })
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    del(userId) {
      this.request.delete("/user/" + userId).then(res => {
        if (res) {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    reset() {
      this.username = ""
      this.degree = ""
      this.rule = ""
      this.load()
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.userId)
      this.request.post("/user/del/batch/", ids).then(res => {
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    exp() {
      window.open(`http://localhost:9090/user/export`)
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    handleExcelImportSuccess() {
      this.$message.success("导入成功")
      this.load()
    }
  }
}
</script>

<style scoped>
.headerBg {
  background: #eee !important;
}
</style>