var gulp = require("gulp");
var del = require('del');
var runSequence = require('run-sequence');
var plugins = require('gulp-load-plugins')({
    pattern: ['gulp-*', 'gulp.*', 'main-bower-files'],
    replaceString: /\bgulp[\-.]/
});


var config = {
    app:  "spring-gulp/src/main/webapp/app/",
    dist: "spring-gulp/src/main/webapp/dist/"
}

var jsFilter = plugins.filter('**/*.js', { restore: true });
var cssFilter = plugins.filter('**/*.css', { restore: true });
var fontFilter = plugins.filter('**/fonts/**', { restore: true });

gulp.task('run', function() {
    console.log('hello world');
});

gulp.task('build', function() {
    //console.log("building");
    runSequence('clean', 'less', 'bower:js', 'bower:css', 'copy:html', 'copy:font', 'useref');
});


gulp.task('clean', function() {
    return del.sync(config.dist);
});

gulp.task('useref', function(){
    return gulp.src([
                        config.app + 'index.html',
                        config.app + 'login.html'
                    ])
            .pipe(plugins.useref())
            .pipe(plugins.if('*.js', plugins.ngAnnotate()))
            //.pipe(plugins.if(['*.js', '!' + config.app + 'vendor/js/**'], plugins.babel({
            //    presets: ['es2015']
            //})))
            .pipe(plugins.if('*.js', plugins.uglify()))
            .pipe(plugins.if('*.css', plugins.cleanCss()))
            .pipe(gulp.dest(config.dist));
});

gulp.task('less', function() {
    return gulp.src(config.app + '/less/**/*.less')
            .pipe(plugins.less())
            .pipe(gulp.dest(config.app + 'css'));
});

gulp.task('bower:js', function() {
    return gulp.src(plugins.mainBowerFiles())
            .pipe(jsFilter)
            .pipe(gulp.dest(config.app + "vendor/js"));
});

gulp.task('bower:css', function() {
    return gulp.src(plugins.mainBowerFiles({
                overrides: {
                    bootstrap: {
                        main: [
                            './dist/js/bootstrap.js',
                            './dist/css/*.css',
                            './dist/fonts/*.*'
                        ]
                    },
                    "font-awesome": {
                        main: [
                            './css/*.css',
                            './fonts/*.*'
                        ]
                    }
                }
            }))
            .pipe(cssFilter)
            .pipe(gulp.dest(config.app + "vendor/css"))
            .pipe(cssFilter.restore)
            .pipe(fontFilter)
            .pipe(gulp.dest(config.app + "vendor/fonts"))
});

gulp.task('copy:html', function() {
    return gulp.src(config.app + "js/**/*.html")
        .pipe(gulp.dest(config.dist + "js"));

});

gulp.task('copy:font', function() {
    return gulp.src(config.app + "vendor/fonts/**/*")
        .pipe(gulp.dest(config.dist + "fonts"));

});

gulp.task('watch', function() {
    gulp.watch(config.app + 'less/**/*.less', ['less']);
});
