/*
 *  Developer module js in here
 *  the first time use OOP js
 */

function Developer() {
    this.initial();
}

Developer.prototype = {
    version:"0.1",
    initial:function () {
        this.toggleMenu();
        this.initialConfirmModal();
    },
    toggleMenu:function () {
        var href = location.href;

        $("#mainMenu li").removeClass("active");
        if (href.indexOf("sprint_") > 0) {
            $("#mainMenu li#sprint_main_menu").addClass("active");
        } else if (href.indexOf("backlog_") > 0) {
            $("#mainMenu li#backlog_main_menu").addClass("active");
        } else if (href.indexOf("project_") > 0) {
            $("#mainMenu li#project_main_menu").addClass("active");
        } else if (href.indexOf("developer_") > 0) {
            $("#mainMenu li#developer_main_menu").addClass("active");
        } else if (href.indexOf("team_") > 0) {
            $("#mainMenu li#team_main_menu").addClass("active");
        } else if (href.indexOf("monitor_") > 0) {
            $("#mainMenu li#monitor_main_menu").addClass("active");
        } else if (href.indexOf("report_") > 0) {
            $("#mainMenu li#report_main_menu").addClass("active");
        } else if (href.indexOf("system/") > 0) {
            $("#mainMenu li#system_main_menu").addClass("active");
        } else {
            $("#mainMenu li:eq(0)").addClass("active");
        }
    },
    initialConfirmModal:function () {
        var self = this;
        $(".confirm").click(function (event) {
            event.preventDefault();
            var $this = $(this);
            var title = self.checkEmptyText($this.attr("confirm-title"), $("span#modalConfirmMessage").html());
            var content = self.checkEmptyText($this.attr("confirm-content"), $("span#modalConfirmContentMessage").html());
            var url = self.checkEmptyText($this.attr("href"), $this.attr("url"));

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html(title);
            $modal.find(".modal-body p").html(content);
            //add click event
            $modal.find("#confirmModalBtn").show().one("click", function (event) {
                event.preventDefault();
                location.href = url;
            });
            $modal.modal("show");
        });
    },
    initialTooltips:function (ele) {
        $(ele || "span[title],a[title],div[title]").tooltip({
            placement:'bottom',
            html:true,
            delay:100
        });
    },
    checkEmptyText:function (origText, defaultText) {
        if (origText === null || $.trim(origText) === '') {
            return defaultText;
        }
        return origText;
    },
    initialAlert:function (eleId, fadeInTime, fadeOutTime) {
        if ('' != eleId) {
            fadeInTime = fadeInTime || 1000;     //default fade in: 1000ms
            fadeOutTime = fadeOutTime || 1000; //default fade out: 1000ms
            var $alert = $("#" + eleId);
            $alert.show().parent().parent().fadeIn(fadeInTime).slideUp(fadeOutTime);
        }
    }
};

/*
 *  developer_home.jsp
 */

function DeveloperHome(alertEleId) {
    this.initialEvents();
    Developer.prototype.initialAlert(alertEleId);
}
DeveloperHome.prototype = new Developer();

DeveloperHome.prototype = {
    initialChart:function (labels, expectDatas, actualDatas, steps, stepWidth) {
        var data = {
            labels:labels,
            datasets:[
                {
                    fillColor:"rgba(255,255,255,0.5)",
                    strokeColor:"rgba(0,0,255,0.8)",
                    pointColor:"rgba(0,0,255,0.8)",
                    pointStrokeColor:"#fff",
                    data:expectDatas
                },
                {
                    fillColor:"rgba(151,187,205,0.4)",
                    strokeColor:"rgba(255,0,0,0.8)",
                    pointColor:"rgba(255,0,0,0.8)",
                    pointStrokeColor:"#fff",
                    data:actualDatas
                }
            ]
        };
        $("#burnDownChart").attr("height", 150).attr("width", 220);
        var context = $("#burnDownChart").get(0).getContext("2d");
        new Chart(context).Line(data, {
            scaleOverride:true,
            scaleStepWidth:stepWidth,
            scaleStartValue:0,
            scaleSteps:steps
        });
    },
    assignTask:function () {
        $("a.assignTask").click(function (event) {
            event.preventDefault();
            var $this = $(this);
            var url = $this.attr("href");
            var loadUrl = $this.attr("load-url");
            var title = $this.attr("modal-title");

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html(title);
            $modal.find(".modal-body p").load(loadUrl, function () {
                //add click event
                $modal.find("#confirmModalBtn").show().click(function (event) {
                    event.preventDefault();
                    var executorGuid = $("#executorFormSelect").val();
                    if ('' == executorGuid) {
                        $("span#assignTaskErrorInfo").removeClass("hidden");
                    } else {
                        location.href = url + "?executorGuid=" + executorGuid;
                    }
                });
                $modal.modal("show");
            });
        });
    },
    initialEvents:function () {
        this.initialSprintChange();
        this.initialTaskTab();
        this.initialTaskClasses();

        Developer.prototype.initialTooltips();
        this.initialPopovers();
        this.finishTaskEvent();

        this.startSprintEvent();
        this.finishSprintEvent();
        this.showBacklogEvent();

        this.burnDownDetailsEvent();
        this.meetingFormEvent();
        this.initialSprintMeetingDetails();

        this.moveTaskEvent();
        this.doTask();
        this.assignTask();

        this.searchTooltip();
        this.developerSearch();
        this.onlyShowMyTasks();

        this.finishedTaskComment();
    },
    finishedTaskComment:function () {
        $("a.finishedTaskComment").click(function (e) {
            e.preventDefault();
            var $this = $(this);
            var loadUrl = $this.attr("load-url");

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html($this.attr("modal-title"));
            $modal.find(".modal-body p").load(loadUrl, function () {
                $modal.find("#confirmModalBtn").hide();
                $modal.modal("show");
            });
        });
    },
    onlyShowMyTasks:function () {
        $("input.onlyShowMyTasks").click(function () {
            var $this = $(this);
            var url = "?currentSprint.guid=" + $("#sprintSelect").val() + "&status=" + $("input#taskStatus").val()
                + "&number=" + $("input#searchInput").val();
            if ($this.attr("checked")) {
                location.href = url + "&onlyShowMyTasks=true";
            } else {
                location.href = url + "&onlyShowMyTasks=false";
            }
        });
    },
    developerSearch:function () {
        $("#developerSearchForm").submit(function () {
            $("input#searchSprintGuid").val($("#sprintSelect").val());
            return true;
        });
    },
    searchTooltip:function () {
        $('input#searchInput').tooltip({
            placement:'bottom'
        });
    },
    initialSprintMeetingDetails:function () {
        $("a.taskMeetingDetails").click(function () {
            var loadUrl = $(this).attr("load-url");

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html($("#meetingDetailsMessage").html());
            $modal.find(".modal-body p").load(loadUrl, function () {
                $modal.find("#confirmModalBtn").hide();
                $modal.modal("show");
            });
        });
    },
    moveTaskEvent:function () {
        var self = this;
        $("a.moveTask").click(function () {
            var loadUrl = $(this).attr("load-url");

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html($("#moveTaskMessage").html());
            $modal.find(".modal-body p").load(loadUrl, function () {
                $modal.find("#confirmModalBtn").show().click(function () {
                    if (self._validateMoveTaskTargetSprint()) {
                        $("#moveTaskForm").submit();
                    }
                });
                $modal.modal("show");
            });
        });
    },
    _validateMoveTaskTargetSprint:function () {
        var targetSprintGuid = $("#moveTaskForm #targetSprintSelect").val();
        if ("" === targetSprintGuid) {
            $("#targetSprintSelectError").show();
            return false;
        }
        return true;
    },
    burnDownDetailsEvent:function () {
        $("#burnDownDetailsId").click(function () {
            var loadUrl = $(this).attr("load-url");

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html($("#burnDownDetailsMessage").html());
            $modal.find(".modal-body p").load(loadUrl, function () {
                $modal.find("#confirmModalBtn").hide();
                $modal.modal("show");
            });
        });
    },
    meetingFormEvent:function () {
        var self = this;
        $("#meetingFormId").click(function () {
            var loadUrl = $(this).attr("load-url");

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html($("#sprintMeetingMessage").html());
            $modal.find(".modal-body p").load(loadUrl, function () {
                $modal.find("#confirmModalBtn").show().bind("click", function () {
                    if (!self._validateMeetingForm()) {
                        $("#sprintMeetingForm").submit();
                    }
                });
                $modal.modal("show");
            });
        });
    },
    _validateMeetingForm:function () {
        var editor = CKEDITOR.instances.content;
        var text = editor.getData();
        var empty_ = (text === "" || $.trim(text) === "");
        if (empty_) {
            $("span#meetingContentWarn").show();
        }
        return empty_;
    },
    showBacklogEvent:function () {
        $(".taskBacklogDetail").click(function () {
            var loadUrl = $(this).attr("load-url");

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html($("#backlogMessage").html());
            $modal.find(".modal-body p").load(loadUrl, function () {
                $modal.find("#confirmModalBtn").hide();
                $modal.modal("show");
            });
        });
    },
    startSprintEvent:function () {
        $("a.startSprint").click(function (event) {
            event.preventDefault();
            var $this = $(this);
            var url = $this.attr("href");
            var loadUrl = $this.attr("load-url");

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html($("#startSprintMessage").html());
            $modal.find(".modal-body p").load(loadUrl, function () {
                $modal.find("#confirmModalBtn").show().one("click", function (event) {
                    event.preventDefault();
                    if ('true' == $("#checkSprintStartPass").val()) {
                        location.href = url;
                    } else {
                        location.href = $("a.editSprintClass").attr("href");
                    }
                });
                $modal.modal("show");
            });

        });
    },
    finishSprintEvent:function () {
        $("a.finishSprint").click(function (event) {
            event.preventDefault();
            var $this = $(this);
            var url = $this.attr("href");
            var loadUrl = $this.attr("load-url");

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html($("#finishSprintMessage").html());
            $modal.find(".modal-body p").load(loadUrl, function () {
                //check finish or not
                var allowFinish = $("#checkSprintAllowFinish").val();
                if ('true' == allowFinish) {
                    $modal.find("#confirmModalBtn").show().one("click", function (event) {
                        event.preventDefault();
                        location.href = url;
                    });
                } else {
                    $modal.find("#confirmModalBtn").hide();
                }
                $modal.modal("show");
            });
        });
    },
    finishTaskEvent:function () {
        $("a.finishTask").click(function (event) {
            event.preventDefault();
            var $this = $(this);
            var url = $this.attr("href");
            var loadUrl = $this.attr("load-url");
            var title = $this.attr("modal-title");

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html(title);
            $modal.find(".modal-body p").load(loadUrl, function () {
                //add click event
                $modal.find("#confirmModalBtn").show().one("click", function (event) {
                    event.preventDefault();
                    location.href = url + "?" + $("#finishSprintTaskForm").serialize();
                });
                $modal.modal("show");
            });
        });
    },
    doTask:function () {
        $("a.doTask").click(function (event) {
            event.preventDefault();
            var $this = $(this);
            var url = $this.attr("href");
            var loadUrl = $this.attr("load-url");
            var title = $this.attr("modal-title");

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html(title);
            $modal.find(".modal-body p").load(loadUrl, function () {
                //add click event
                $modal.find("#confirmModalBtn").show().one("click", function (event) {
                    event.preventDefault();
                    location.href = url;
                });
                $modal.modal("show");
            });
        });
    },
    initialPopovers:function () {
        $("span.taskDetails").popover({
            html:true,
            delay:200,
            placement:'left',
            title:$("#taskDetailsMessage").html()
        });
    },
    initialTaskClasses:function () {
        $("table#task .urgent").parent().addClass("error");
        $("table#task .FINISHED").parent().addClass("success");
        $("table#task .CANCELED").parent().addClass("warning");
        $("table#task .PENDING").parent().addClass("info");
    },
    initialTaskTab:function () {
        $(".taskTab").click(function (event) {
            event.preventDefault();
            $("input#taskStatus").val($(this).attr("task-status"));
            $("#developerForm").submit();
        });
    },
    initialSprintChange:function () {
        $("#sprintSelect").change(function () {
            this.form.submit();
        });
    }
};

/*
 *  sprint_form.jsp
 */
function SprintForm() {
    this.initialEvents();
}
SprintForm.prototype = new Developer();

SprintForm.prototype = {
    initialEvents:function () {
        this.changeAddTaskEvent();
        this.initialBudgetBacklogTimes();
    },
    initialBudgetBacklogTimes:function () {
        var self = this;
        $("#backlogUl li input:checkbox").click(function () {
            var $this = $(this);
            if ($this.attr("checked")) {
                $this.parent().find(".estimateTime").addClass("backlogEstTime");
            } else {
                $this.parent().find(".estimateTime").removeClass("backlogEstTime");
            }
            self._showBudgetBacklogTimes();
        });
    },
    _showBudgetBacklogTimes:function () {
        var total = 0;
        $("#backlogUl li .backlogEstTime").each(function () {
            total += parseInt($.trim($(this).html()));
        });
        $("#budgetTimes strong").html(total);
    },
    changeAddTaskEvent:function () {
        $("button[type='submit']").click(function () {
            $("#addTask").val($(this).attr("addTask"));
        });
    },
    initialDatePicker:function (startDate, language) {
        $('#startDate,#deadline').datepicker({
            format:'yyyy-mm-dd',
            autoclose:true,
            language:language,
            startDate:startDate,
            weekStart:1
        });
    }
};

/*
 *  backlog_form.jsp
 */
function BacklogForm(alertEleId) {
    this.initialEvents();
    Developer.prototype.initialAlert(alertEleId);
}
BacklogForm.prototype = new Developer();

BacklogForm.prototype = {
    initialEvents:function () {
        this.changeAddTaskEvent();
    },
    changeAddTaskEvent:function () {
        $("button[type='submit']").click(function () {
            $("#addNext").val($(this).attr("addNext"));
        });
    }
};

/*
 *  sprint_task_form.jsp
 */
function SprintTaskForm(alertEleId) {
    this.initialEvents();
    Developer.prototype.initialAlert(alertEleId);
}
SprintTaskForm.prototype = new Developer();

SprintTaskForm.prototype = {
    initialEvents:function () {
        this.changeAddTaskEvent();
    },
    changeAddTaskEvent:function () {
        $("button[type='submit']").click(function () {
            $("#addNext").val($(this).attr("addNext"));
        });
    }
};

/*
 *  backlog_overview.jsp
 */
function BacklogOverview(alertEleId) {
    this.initialEvents();
    Developer.prototype.initialAlert(alertEleId);
    Developer.prototype.initialTooltips();
}
BacklogOverview.prototype = new Developer();

BacklogOverview.prototype = {
    initialPopovers:function () {
//        $(".backlogDetails").popover({
//            html:true,
//            delay:200,
//            placement:'left',
//            title:"Backlog details"
//        });
    }, initialBacklogClasses:function () {
        $("table#backlog .HIGH").parent().addClass("error");
    }, initialEvents:function () {
        this.initialPopovers();
        this.initialBacklogClasses();
    }
};


/*
 *  sprint_overview.jsp
 */
function SprintOverview(alertEleId) {
    this.initialEvents();
    Developer.prototype.initialAlert(alertEleId);
}
SprintOverview.prototype = new Developer();

SprintOverview.prototype = {
    createSprint:function () {
        $("#createSprint").click(function (event) {
            event.preventDefault();
            var $this = $(this);
            var url = $this.attr("href");
            var projectGuid = $this.attr("projectGuid");
            if ('' == projectGuid) {
                var loadUrl = $this.attr("load-url");
                var title = $this.attr("modal-title");

                var $modal = $("#developerModal");
                $modal.find("#developerModalLabel").html(title);
                $modal.find(".modal-body p").load(loadUrl, function () {
                    //add click event
                    $modal.find("#confirmModalBtn").show().click(function (event) {
                        event.preventDefault();
                        projectGuid = $("select#projectSprintSelect").val();
                        if (null === projectGuid || '' === projectGuid) {
                            $("span#projectSprintSelectError").removeClass("hide");
                            return;
                        }
                        location.href = url + "?pGuid=" + projectGuid;
                    });
                    $modal.modal("show");
                });
            } else {
                location.href = url + "?pGuid=" + projectGuid;
            }
        });
    },
    initialPopovers:function () {
//        $(".sprintDetails").popover({
//            html:true,
//            delay:200,
//            placement:'left',
//            title:"Sprint details"
//        });
    },
    showBacklogEvent:function () {
        $(".sprintBacklogDetails").click(function () {
            var loadUrl = $(this).attr("load-url");

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html($("#sprintBacklogsMessage").html());
            $modal.find(".modal-body p").load(loadUrl, function () {
                $modal.modal("show");
                $modal.find("#confirmModalBtn").hide();
            });

        });
    },
    startSprintEvent:function () {
        $("a.startSprint").click(function (event) {
            event.preventDefault();
            var $this = $(this);
            var url = $this.attr("href");
            var loadUrl = $this.attr("load-url");

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html($("#startSprintMessage").html());
            $modal.find(".modal-body p").load(loadUrl, function () {
                //add click event
                $modal.find("#confirmModalBtn").one("click", function (event) {
                    event.preventDefault();
                    if ('true' == $("#checkSprintStartPass").val()) {
                        location.href = url;
                    } else {
                        location.href = $("a.editSprintClass").attr("href");
                    }
                });
                $modal.modal("show");
            });
        });
    },
    finishSprintEvent:function () {
        $("a.finishSprint").click(function (event) {
            event.preventDefault();
            var $this = $(this);
            var url = $this.attr("href");
            var loadUrl = $this.attr("load-url");

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html($("#finishSprintMessage").html());
            $modal.find(".modal-body p").load(loadUrl, function () {
                //check finish or not
                var allowFinish = $("#checkSprintAllowFinish").val();
                if ('true' == allowFinish) {
                    $modal.find("#confirmModalBtn").one("click", function (event) {
                        event.preventDefault();
                        location.href = url;
                    });
                } else {
                    $modal.find("#confirmModalBtn").hide();
                }
            });
            $modal.modal("show");
        });
    },
    statusChangeEvent:function () {
        $("#sprintStatusSelect").change(function () {
            $("#sprintForm").submit();
        });
    },
    initialSprintClasses:function () {
        $("table#sprint .FINISHED").parent().addClass("success");
        $("table#sprint .PENDING").parent().addClass("info");
    },
    initialEvents:function () {
        this.initialPopovers();
        this.statusChangeEvent();
        this.initialSprintClasses();
        this.showBacklogEvent();

        this.startSprintEvent();
        this.finishSprintEvent();
        Developer.prototype.initialTooltips();
        this.createSprint();
    }
};

/*
 *  project_overview.jsp
 */
function ProjectOverview(alertEleId) {
    Developer.prototype.initialAlert(alertEleId);
    this.initialEvents();
}

ProjectOverview.prototype = new Developer();
ProjectOverview.prototype = {
    initialEvents:function () {
        this.initialPopovers();
    },
    initialPopovers:function () {
        $(".projectDetails").popover({
            html:true,
            delay:200,
            placement:'left',
            title:$("#projectDescMessage").html()
        });
    }
};

/*
 *  project_form.jsp
 */
function ProjectForm(language) {
    this.initialDatePicker(language);
}

ProjectForm.prototype = new Developer();
ProjectForm.prototype = {
    initialDatePicker:function (language) {
        $('#finishDate').datepicker({
            format:'yyyy-mm-dd',
            autoclose:true,
            language:language,
            weekStart:1
        });
        $('#startDate').datepicker({
            format:'yyyy-mm-dd',
            autoclose:true,
            language:language,
            weekStart:1
        });
    }
};

/*
 *  sprint_meeting_overview.jsp
 */
function SprintMeetingOverview(alertEleId) {
    this.initialEvents();
    Developer.prototype.initialAlert(alertEleId);
    Developer.prototype.initialTooltips();
}
SprintMeetingOverview.prototype = new Developer();

SprintMeetingOverview.prototype = {
    initialEvents:function () {
        this.addMeetingForm();
        this.initialTypeClass();

        this.editMeetingEvent();
        this.meetingDetailsEvent();
    },
    meetingDetailsEvent:function () {
        $("a.meetingDetails").click(function () {
            var loadUrl = $(this).attr("load-url");

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html($("#meetingDetailsMessage").html());
            $modal.find(".modal-body p").load(loadUrl, function () {
                $modal.find("#confirmModalBtn").hide();
                $modal.modal("show");
            });
        });
    },
    editMeetingEvent:function () {
        var self = this;
        $("a.meetingEdit").click(function () {
            var loadUrl = $(this).attr("load-url");
            self._meetingForm(loadUrl);
        });
    },
    initialTypeClass:function () {
        $("table#meeting .DAILY_STANDING").parent().addClass("info");
        $("table#meeting .SPRINT_REVIEW").parent().addClass("success");
        $("table#meeting .SPRINT_PLANNING").parent().addClass("warning");
    },
    addMeetingForm:function () {
        var self = this;
        $("#sprintMeetingFormId").click(function () {
            var loadUrl = $(this).attr("load-url");
            self._meetingForm(loadUrl);
        });
    },
    _meetingForm:function (loadUrl) {
        var self = this;
        var $modal = $("#developerModal");
        $modal.find("#developerModalLabel").html($("#sprintMeetingMessage").html());
        $modal.find(".modal-body p").load(loadUrl, function () {
            $("input#meetingBackToOverview").val(true);
            $modal.find("#confirmModalBtn").show().bind("click", function () {
                if (!self._validateMeetingForm()) {
                    $("#sprintMeetingForm").submit();
                }
            });
            $modal.modal("show");
        });
    },
    _validateMeetingForm:function () {
        var editor = CKEDITOR.instances.content;
        var text = editor.getData();
        var empty_ = (text === "" || $.trim(text) === "");
        if (empty_) {
            $("span#meetingContentWarn").show();
        }
        return empty_;
    }
};

/*
 *  finish_task_form.jsp
 */
function FinishTaskForm() {
    this.bindEvents();
}

FinishTaskForm.prototype = {
    bindEvents:function () {
        $("#finishSprintTaskForm input[name='larger4Hours']").click(function () {
            if ($(this).attr("checked")) {
                $("#finishSprintTaskForm #actualUsedTimeSelect").attr("disabled", true);
            } else {
                $("#finishSprintTaskForm #actualUsedTimeSelect").attr("disabled", false);
            }
        });
    }
};

/*
 *  user_overview.jsp
 */

function UserOverview(alertEleId) {
    Developer.prototype.initialAlert(alertEleId);
    this.resetPassword();
}

UserOverview.prototype = {
    resetPassword:function () {
        $(".resetPassword").click(function (event) {
            event.preventDefault();
            var $this = $(this);

            var content = $this.attr("confirm-content");
            var url = $this.attr("href");

            var $modal = $("#developerModal");
            $modal.find("#developerModalLabel").html($("#resetPassTitleMessage").html());
            $modal.find(".modal-body p").html(content);
            //add click event
            $modal.find("#confirmModalBtn").show().one("click", function (event) {
                event.preventDefault();
                $.get(url, function (data) {
                    $modal.find(".modal-body p").html(data);
                    $modal.find("#confirmModalBtn").hide();
                });
            });
            $modal.modal("show");
        });
    }
};

/*
 *  user_form.jsp
 */

function UserForm(alertEleId) {
    Developer.prototype.initialAlert(alertEleId);
    this.bindEvents();
}

UserForm.prototype = {
    bindEvents:function () {
        this.addNextEvent();
    },
    addNextEvent:function () {
        $("button[type='submit']").click(function () {
            $("#addNext").val($(this).attr("addNext"));
        });
    },
    setPasswordValues:function (pass, rePass) {
        $("#password").val(pass);
        $("#rePassword").val(rePass);
    }
};


/*
 *  team_overview.jsp
 */

function TeamOverview(alertEleId) {
    Developer.prototype.initialAlert(alertEleId);
    this.initialPopovers();
}

TeamOverview.prototype = {
    initialPopovers:function () {
        var $2 = $(".teamDetails");
        var title = $2.attr("teamName");
        $2.popover({
            html:true,
            delay:200,
            placement:'left',
            title:title
        });
    }
};

/*
 *  my_profile.jsp
 */

function MyProfile(alertEleId) {
    Developer.prototype.initialAlert(alertEleId);
}

/*
 *  system_config.jsp
 */

function SystemConfig(alertEleId) {
    Developer.prototype.initialAlert(alertEleId);
}

/*
 *  sprint_time_report.jsp
 * @constructor
 */
function SprintTimeReport() {
    this.sprintSelect();
}

SprintTimeReport.prototype = {
    sprintSelect:function () {
        $("#sprintSelect").change(function () {
            location.href = "./" + $(this).val();
        });
    }
};


/*
 *  sprint_manage_backlogs.jsp
 * @constructor
 */
function SprintManageBacklogs() {
    this.sprintBacklogSelect();
    this.unreferSelect();
    this.moveToUnused();
    this.moveToSprint();
}

SprintManageBacklogs.prototype = {
    moveToUnused:function () {
        $("button#moveToUnused").click(function () {
            var $this = $(this);
            var sprintGuid = $("input#sprintGuid").val();
            var backlogGuid = $("select#sprintSelect").val();

            if (confirm($("#confirmRemoveBacklogMessage").html())) {
                $.get("move_sprint_backlog", {sprintGuid:sprintGuid, backlogGuid:backlogGuid}, function (data) {
                    if (data.result) {
                        var $sprintOption = $("select#sprintSelect option:selected");
                        var $newOption = $sprintOption.clone();
                        $sprintOption.remove();
                        $("select#unreferSelect").append($newOption);
                        $this.attr("disabled", true);
                    } else {
                        alert($("#removeBacklogFailedMessage").html());
                    }
                });
            }
        });
    },
    moveToSprint:function () {
        $("button#moveToSprint").click(function () {
            var $this = $(this);
            var sprintGuid = $("input#sprintGuid").val();
            var backlogGuid = $("select#unreferSelect").val();

            if (confirm($("#addBacklogMessage").html())) {
                $.get("move_sprint_backlog", {sprintGuid:sprintGuid, backlogGuid:backlogGuid, inverse:true}, function (data) {
                    if (data.result) {
                        var $sprintOption = $("select#unreferSelect option:selected");
                        var $newOption = $sprintOption.clone();
                        $sprintOption.remove();
                        $("select#sprintSelect").append($newOption);
                        $this.attr("disabled", true);
                    } else {
                        alert($("#addBacklogFailedMessage").html());
                    }
                });
            }
        });
    },
    sprintBacklogSelect:function () {
        $("select#sprintSelect").change(function () {
            if ('' != $(this).val()) {
                $("button#moveToUnused").attr("disabled", false);
            }
        });
    },
    unreferSelect:function () {
        $("select#unreferSelect").change(function () {
            if ('' != $(this).val()) {
                $("button#moveToSprint").attr("disabled", false);
            }
        });
    }
};


//------------------------------------------------------------------------------------

/**
 * displaytag paginated use  it.
 * Don't change it
 *
 * @param formId
 * @param data
 */
function displaytagform(formId, data) {
    var params = '?';
    $.map(data, function (d) {
        params += (d.f + "=" + d.v + "&");
    });
    var $form = $("#" + formId);
    var action = $form.attr("action");
    var url = action + params;
    var $targetDiv = $form.parent().find(".displayTarget");
    if ($targetDiv.length > 0) {
        //if exist, load  the content to the div
        $targetDiv.load(url);
    } else {
        location.href = url;
    }
}

/*
 *  Call initial js
 * */

$(function () {
    new Developer();
});