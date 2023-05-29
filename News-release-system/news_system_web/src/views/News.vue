<template>
  <div>
    <div style="padding: 10px 0">
      <el-input style="width: 200px;" placeholder="标题" suffix-icon="el-icon-search" class="ml-5"
                v-model="title"></el-input>
      <el-input style="width: 200px;" placeholder="类型" suffix-icon="el-icon-document" class="ml-20"
                v-model="type"></el-input>
      <el-input style="width: 200px;" placeholder="审核状态" suffix-icon="el-icon-user
" class="ml-20" v-model="isAudit">
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
    </div>

    <el-table
        :data="tableData" border stripe :header-cell-class-name="'headerBg'"
        @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55px">
      </el-table-column>
      <el-table-column prop="id" label="编号"></el-table-column>
      <el-table-column prop="title" label="标题"></el-table-column>
      <el-table-column prop="type" label="类型"></el-table-column>
      <el-table-column prop="userId" label="发布人id"></el-table-column>
      <el-table-column prop="isAudit" label="审核状态">
        <template slot-scope="scope">
          <p>启用：
            <el-switch
                :active-value="1"
                :inactive-value="0"
                v-model="scope.row.isAudit"
                @change="">
            </el-switch>
          </p>
        </template>
      </el-table-column>
      <el-table-column prop="top" label="是否热门">
        <template slot-scope="scope">
          <p>热门：
            <el-switch
                :active-value="1"
                :inactive-value="0"
                v-model="scope.row.top"
                @change="">
            </el-switch>
          </p>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="380" aligin="center">
        <temple slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">查看详情</el-button>
          <el-popconfirm
              class="ml-10"
              confirmButtonText='确定'
              cancelButtonText='不用了'
              icon="el-icon-info"
              iconColor="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.id)">
            <el-button type="danger" slot="reference">删除<i class="el-icon-delete"></i></el-button>
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
    <el-dialog title="新闻详情" :visible.sync="dialogFormVisible" width="50%">
      <el-form :model="form" label-width="80px">
        <el-form-item label="标题">
          <el-input v-model="form.title"></el-input>
        </el-form-item>
        <el-form-item label="内容">
          <el-input v-model="form.content" type="textarea" :rows="10"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "news",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 8,
      id: "",
      title: "",
      content: "",
      type: "",
      top: "",
      userId: "",
      isAudit: "",
      dialogFormVisible: false,
      form: {}
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/news/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          title: this.title,
          type: this.type,
          isAudit: this.isAudit
        }
      }).then(res => {
        //console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    save() {
      this.request.post("/news", this.form).then(res => {
        if (res) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
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
    del(id) {
      console.log(id)
      this.request.delete("/news/" + id).then(res => {
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
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)
      this.request.post("/news/del/batch/", ids).then(res => {
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    // 选中项发生变化时会触发该事件
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    // 每页显示条数改变时会触发该事件
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    // 当前页发生改变时会触发该事件
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    }
  }
}
</script>

<style scoped>
.headerBg {
  background: #eee !important;
}
.my-input {
  width: 100%;
  height: 200px;
  resize: none;
}
</style>