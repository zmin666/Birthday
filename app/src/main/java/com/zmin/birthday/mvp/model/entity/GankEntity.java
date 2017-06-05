package com.zmin.birthday.mvp.model.entity;

import java.util.List;

/**
 * @author: ZhangMin
 * @date: 2017/6/5 16:01
 * @desc:
 */
public class GankEntity {

    /**
     * error : false
     * results : [{"_id":"5933c14f421aa92c79463358","createdAt":"2017-06-04T16:14:07.731Z","desc":"从一个大型项目中快速找到当前页面元素用到的布局文件","images":["http://img.gank.io/37550fa9-01ab-43b9-98c6-8e2d3c78745a"],"publishedAt":"2017-06-05T11:44:53.909Z","source":"web","type":"Android","url":"https://github.com/nekocode/ResourceInspector","used":true,"who":"nekocode"},{"_id":"59340e11421aa92c7be61b9d","createdAt":"2017-06-04T21:41:37.94Z","desc":"Retrofit 2.0 超能实践（三），轻松实现多文件/图片上传/Json字符串/表单","publishedAt":"2017-06-05T11:44:53.909Z","source":"api","type":"Android","url":"http://www.jianshu.com/p/acfefb0a204f","used":true,"who":"Tamic"},{"_id":"59341cc1421aa92c7946335c","createdAt":"2017-06-04T22:44:17.163Z","desc":"Android卡顿检测方案（很简单的实现方案）","images":["http://img.gank.io/b60e943c-644a-48f2-be2d-cbf9a89850c1"],"publishedAt":"2017-06-05T11:44:53.909Z","source":"web","type":"Android","url":"http://blog.coderclock.com/2017/06/04/android/AndroidPerformanceTools-BlockLooper/","used":true,"who":"D_clock"},{"_id":"592d32fd421aa92c7be61b71","createdAt":"2017-05-30T16:53:17.659Z","desc":"Learn Kotlin\u200a\u2014\u200aExtension Functions","images":["http://img.gank.io/e94a62f3-8b9a-40ed-a25e-08f25114524a"],"publishedAt":"2017-06-03T11:15:41.272Z","source":"web","type":"Android","url":"https://mindorks.com/blog/learn-kotlin-extension-functions","used":true,"who":"AMIT SHEKHAR"},{"_id":"592f6bfa421aa92c7be61b7d","createdAt":"2017-06-01T09:20:58.501Z","desc":"给初学者的RxJava2.0教程（九）：响应式拉取","publishedAt":"2017-06-03T11:15:41.272Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247484765&idx=1&sn=518fbb085cda7ed7718ca101c286814b&chksm=96cda410a1ba2d061e8cb42503625c6f11ed9bfc6d2c1d118101b85c9cfe45409be12325e78f#rd","used":true,"who":"陈宇明"},{"_id":"59301159421aa92c73b647a0","createdAt":"2017-06-01T21:06:33.34Z","desc":"我是如何实现的一个漂亮的笑脸评分动画效果的，超赞的一篇文章。","publishedAt":"2017-06-03T11:15:41.272Z","source":"web","type":"Android","url":"https://blog.mindorks.com/android-smileyrating-how-i-solved-it-9b5ee30f2c34","used":true,"who":"Ali"},{"_id":"59322476421aa92c79463355","createdAt":"2017-06-03T10:52:38.588Z","desc":"轻量级 Android Nosql 方案，简单的 App 用这个完全够了！","publishedAt":"2017-06-03T11:15:41.272Z","source":"api","type":"Android","url":"https://github.com/florent37/Android-NoSql","used":true,"who":"allen"},{"_id":"59322691421aa92c769a8c04","createdAt":"2017-06-03T11:01:37.307Z","desc":"过渡平滑的 Toolbar 效果","images":["http://img.gank.io/cb9ca61e-e92c-43c1-8a5e-5803864e6066"],"publishedAt":"2017-06-03T11:15:41.272Z","source":"api","type":"Android","url":"https://github.com/arsan-official/collapsing-toolbar","used":true,"who":"allen"},{"_id":"59269b5d421aa92c7946331a","createdAt":"2017-05-25T16:52:45.395Z","desc":"Google默认为RecyclerView提供了LinearLayoutManager、StaggeredGridLayoutManager、GridLayoutManager，已经可以满足很多开发需求了，但是实际开发过程中，免不了出现一些更加新颖的交互设计。于是，便从Github上整理了一波关于RecyclerView可以使用的LayoutManager，在实际开发中需要用到时，遇到相似的效果时即可随手拈来，提高效率。","publishedAt":"2017-06-02T12:26:37.346Z","source":"web","type":"Android","url":"http://blog.coderclock.com/2017/03/26/android/%E5%85%B3%E4%BA%8EAndroid%20RecyclerView%E7%9A%84%E9%82%A3%E4%BA%9B%E5%BC%80%E6%BA%90LayoutManager/","used":true,"who":"D_clock"},{"_id":"592fcd8d421aa92c73b6479a","createdAt":"2017-06-01T16:17:17.671Z","desc":"基于MVVM架构,采用kotlin开发的豆瓣电影TOP250","images":["http://img.gank.io/e66bc328-9c53-4517-b6fa-a0f0fc0be465"],"publishedAt":"2017-06-02T12:26:37.346Z","source":"web","type":"Android","url":"https://github.com/zhoujunjiang/DoubanMovie","used":true,"who":"Aid913"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5933c14f421aa92c79463358
         * createdAt : 2017-06-04T16:14:07.731Z
         * desc : 从一个大型项目中快速找到当前页面元素用到的布局文件
         * images : ["http://img.gank.io/37550fa9-01ab-43b9-98c6-8e2d3c78745a"]
         * publishedAt : 2017-06-05T11:44:53.909Z
         * source : web
         * type : Android
         * url : https://github.com/nekocode/ResourceInspector
         * used : true
         * who : nekocode
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
