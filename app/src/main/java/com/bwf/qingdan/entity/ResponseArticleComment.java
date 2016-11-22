package com.bwf.qingdan.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/28.
 */

public class ResponseArticleComment {

    /**
     * code : 0
     * message : Success
     * data : {"comments":[{"id":3168,"target_type":1,"target_id":848,"body":"刚刚装修完单因为租房到期没法续租只放了一个月左右就入住了。虽然装了新风系统，虽然为了环保放弃了工厂定制的好看的板式家具让木工在家按照尺寸做的实木家具，虽然选择了实木地板，但是看到整个装修过程就是在给所有有洞有缝隙的地方打胶就害怕。合着我住的是一个被胶水填充的房子，就像穿了一件满是补丁的衣服。现在空气好就开窗，空气不好就开新风，每天都在与甲醛同在啊！","rootId":0,"parentId":0,"level":1,"replyCount":0,"isUpvoted":false,"upvoteCount":1,"createdAt":"2016-10-27 11:07:24","createdAtDiffForHumans":"23小时前","user":{"id":26318,"username":"fa-fa","nickname":"发发","tagline":"想要去清单上班的一个整理癖收纳癖买买提用户","avatarUrl":"http://img01.eqingdan.com/b78b1f4a-9bf2-11e6-847c-00163e004c5e.jpeg?imageView2/1/w/120/h/120/q/75","backgroundImageUrl":null,"weibo":null,"wechat":null}},{"id":3153,"target_type":1,"target_id":848,"body":"怎么回事？几篇文章看着都不全，滑到下面就是评论区就翻不动了，文章只显示开头\u2026\u2026","rootId":0,"parentId":0,"level":1,"replyCount":0,"isUpvoted":false,"upvoteCount":1,"createdAt":"2016-10-26 13:25:36","createdAtDiffForHumans":"1天前","user":{"id":26896,"username":"mo-yu-qing-lan","nickname":"茉屿清澜","tagline":"","avatarUrl":"http://img01.eqingdan.com/e9340d0e-4edb-11e6-9f78-00163e002745.jpeg?imageView2/1/w/120/h/120/q/75","backgroundImageUrl":null,"weibo":{"uid":"2658089853","name":"茉屿清澜"},"wechat":null}}],"meta":{"pagination":{"total":2,"count":2,"per_page":4,"current_page":1,"total_pages":1,"links":{}}}}
     */

    private int code;
    private String message;
    /**
     * comments : [{"id":3168,"target_type":1,"target_id":848,"body":"刚刚装修完单因为租房到期没法续租只放了一个月左右就入住了。虽然装了新风系统，虽然为了环保放弃了工厂定制的好看的板式家具让木工在家按照尺寸做的实木家具，虽然选择了实木地板，但是看到整个装修过程就是在给所有有洞有缝隙的地方打胶就害怕。合着我住的是一个被胶水填充的房子，就像穿了一件满是补丁的衣服。现在空气好就开窗，空气不好就开新风，每天都在与甲醛同在啊！","rootId":0,"parentId":0,"level":1,"replyCount":0,"isUpvoted":false,"upvoteCount":1,"createdAt":"2016-10-27 11:07:24","createdAtDiffForHumans":"23小时前","user":{"id":26318,"username":"fa-fa","nickname":"发发","tagline":"想要去清单上班的一个整理癖收纳癖买买提用户","avatarUrl":"http://img01.eqingdan.com/b78b1f4a-9bf2-11e6-847c-00163e004c5e.jpeg?imageView2/1/w/120/h/120/q/75","backgroundImageUrl":null,"weibo":null,"wechat":null}},{"id":3153,"target_type":1,"target_id":848,"body":"怎么回事？几篇文章看着都不全，滑到下面就是评论区就翻不动了，文章只显示开头\u2026\u2026","rootId":0,"parentId":0,"level":1,"replyCount":0,"isUpvoted":false,"upvoteCount":1,"createdAt":"2016-10-26 13:25:36","createdAtDiffForHumans":"1天前","user":{"id":26896,"username":"mo-yu-qing-lan","nickname":"茉屿清澜","tagline":"","avatarUrl":"http://img01.eqingdan.com/e9340d0e-4edb-11e6-9f78-00163e002745.jpeg?imageView2/1/w/120/h/120/q/75","backgroundImageUrl":null,"weibo":{"uid":"2658089853","name":"茉屿清澜"},"wechat":null}}]
     * meta : {"pagination":{"total":2,"count":2,"per_page":4,"current_page":1,"total_pages":1,"links":{}}}
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pagination : {"total":2,"count":2,"per_page":4,"current_page":1,"total_pages":1,"links":{}}
         */

        private MetaBean meta;
        /**
         * id : 3168
         * target_type : 1
         * target_id : 848
         * body : 刚刚装修完单因为租房到期没法续租只放了一个月左右就入住了。虽然装了新风系统，虽然为了环保放弃了工厂定制的好看的板式家具让木工在家按照尺寸做的实木家具，虽然选择了实木地板，但是看到整个装修过程就是在给所有有洞有缝隙的地方打胶就害怕。合着我住的是一个被胶水填充的房子，就像穿了一件满是补丁的衣服。现在空气好就开窗，空气不好就开新风，每天都在与甲醛同在啊！
         * rootId : 0
         * parentId : 0
         * level : 1
         * replyCount : 0
         * isUpvoted : false
         * upvoteCount : 1
         * createdAt : 2016-10-27 11:07:24
         * createdAtDiffForHumans : 23小时前
         * user : {"id":26318,"username":"fa-fa","nickname":"发发","tagline":"想要去清单上班的一个整理癖收纳癖买买提用户","avatarUrl":"http://img01.eqingdan.com/b78b1f4a-9bf2-11e6-847c-00163e004c5e.jpeg?imageView2/1/w/120/h/120/q/75","backgroundImageUrl":null,"weibo":null,"wechat":null}
         */

        private List<CommentsBean> comments;

        public MetaBean getMeta() {
            return meta;
        }

        public void setMeta(MetaBean meta) {
            this.meta = meta;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class MetaBean {
            /**
             * total : 2
             * count : 2
             * per_page : 4
             * current_page : 1
             * total_pages : 1
             * links : {}
             */

            private PaginationBean pagination;

            public PaginationBean getPagination() {
                return pagination;
            }

            public void setPagination(PaginationBean pagination) {
                this.pagination = pagination;
            }

            public static class PaginationBean {
                private int total;
                private int count;
                private int per_page;
                private int current_page;
                private int total_pages;

                public int getTotal() {
                    return total;
                }

                public void setTotal(int total) {
                    this.total = total;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public int getPer_page() {
                    return per_page;
                }

                public void setPer_page(int per_page) {
                    this.per_page = per_page;
                }

                public int getCurrent_page() {
                    return current_page;
                }

                public void setCurrent_page(int current_page) {
                    this.current_page = current_page;
                }

                public int getTotal_pages() {
                    return total_pages;
                }

                public void setTotal_pages(int total_pages) {
                    this.total_pages = total_pages;
                }
            }
        }

        public static class CommentsBean {
            private int id;
            private int target_type;
            private int target_id;
            private String body;
            private int rootId;
            private int parentId;
            private int level;
            private int replyCount;
            private boolean isUpvoted;
            private int upvoteCount;
            private String createdAt;
            private String createdAtDiffForHumans;
            /**
             * id : 26318
             * username : fa-fa
             * nickname : 发发
             * tagline : 想要去清单上班的一个整理癖收纳癖买买提用户
             * avatarUrl : http://img01.eqingdan.com/b78b1f4a-9bf2-11e6-847c-00163e004c5e.jpeg?imageView2/1/w/120/h/120/q/75
             * backgroundImageUrl : null
             * weibo : null
             * wechat : null
             */

            private UserBean user;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTarget_type() {
                return target_type;
            }

            public void setTarget_type(int target_type) {
                this.target_type = target_type;
            }

            public int getTarget_id() {
                return target_id;
            }

            public void setTarget_id(int target_id) {
                this.target_id = target_id;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public int getRootId() {
                return rootId;
            }

            public void setRootId(int rootId) {
                this.rootId = rootId;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getReplyCount() {
                return replyCount;
            }

            public void setReplyCount(int replyCount) {
                this.replyCount = replyCount;
            }

            public boolean isIsUpvoted() {
                return isUpvoted;
            }

            public void setIsUpvoted(boolean isUpvoted) {
                this.isUpvoted = isUpvoted;
            }

            public int getUpvoteCount() {
                return upvoteCount;
            }

            public void setUpvoteCount(int upvoteCount) {
                this.upvoteCount = upvoteCount;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getCreatedAtDiffForHumans() {
                return createdAtDiffForHumans;
            }

            public void setCreatedAtDiffForHumans(String createdAtDiffForHumans) {
                this.createdAtDiffForHumans = createdAtDiffForHumans;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class UserBean {
                private int id;
                private String username;
                private String nickname;
                private String tagline;
                private String avatarUrl;
                private Object backgroundImageUrl;
                private Object weibo;
                private Object wechat;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getTagline() {
                    return tagline;
                }

                public void setTagline(String tagline) {
                    this.tagline = tagline;
                }

                public String getAvatarUrl() {
                    return avatarUrl;
                }

                public void setAvatarUrl(String avatarUrl) {
                    this.avatarUrl = avatarUrl;
                }

                public Object getBackgroundImageUrl() {
                    return backgroundImageUrl;
                }

                public void setBackgroundImageUrl(Object backgroundImageUrl) {
                    this.backgroundImageUrl = backgroundImageUrl;
                }

                public Object getWeibo() {
                    return weibo;
                }

                public void setWeibo(Object weibo) {
                    this.weibo = weibo;
                }

                public Object getWechat() {
                    return wechat;
                }

                public void setWechat(Object wechat) {
                    this.wechat = wechat;
                }
            }
        }
    }
}
