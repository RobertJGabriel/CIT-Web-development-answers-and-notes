var gulp = require('gulp'),
    path = require('path'),
    concat = require('gulp-concat'),
    folders = require('gulp-folders'),
    rjs = require('gulp-requirejs'),
    clean = require('gulp-clean'),
    NwBuilder = require('nw-builder'),
    gutil = require('gulp-util'),
    less = require('gulp-less'),
    minifyCSS = require('gulp-minify-css');





gulp.task('less', function () {
    gulp.src('./assets/less/style.less')
        .pipe(less()
            .on('error', gutil.log)
            .on('error', gutil.beep)
            .on('error', function (err) {
                console.log('err', err);
                var pathToFile = err.fileName.split('\\');
                file = pathToFile[pathToFile.length - 1];
         
            })
        )
        .pipe(minifyCSS({
            keepSpecialComments: 1
        }))
        .pipe(gulp.dest('./assets/dist/'));
});


// Default Task
gulp.task('default', ['less']);