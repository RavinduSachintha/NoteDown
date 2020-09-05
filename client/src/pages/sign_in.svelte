<script>
    let usernameOrEmail = '';
    let password = '';
</script>

<style>
    /* ANIMATIONS */

    /* Simple CSS3 Fade-in-down Animation */
    .fadeInDown {
        -webkit-animation-name: fadeInDown;
        animation-name: fadeInDown;
        -webkit-animation-duration: 1s;
        animation-duration: 1s;
        -webkit-animation-fill-mode: both;
        animation-fill-mode: both;
    }

    @-webkit-keyframes fadeInDown {
        0% {
            opacity: 0;
            -webkit-transform: translate3d(0, -100%, 0);
            transform: translate3d(0, -100%, 0);
        }
        100% {
            opacity: 1;
            -webkit-transform: none;
            transform: none;
        }
    }

    @keyframes fadeInDown {
        0% {
            opacity: 0;
            -webkit-transform: translate3d(0, -100%, 0);
            transform: translate3d(0, -100%, 0);
        }
        100% {
            opacity: 1;
            -webkit-transform: none;
            transform: none;
        }
    }

    /* Simple CSS3 Fade-in Animation */
    @-webkit-keyframes fadeIn {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }

    @-moz-keyframes fadeIn {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }

    @keyframes fadeIn {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }

    .fadeIn {
        opacity: 0;
        -webkit-animation: fadeIn ease-in 1;
        -moz-animation: fadeIn ease-in 1;
        animation: fadeIn ease-in 1;

        -webkit-animation-fill-mode: forwards;
        -moz-animation-fill-mode: forwards;
        animation-fill-mode: forwards;

        -webkit-animation-duration: 1s;
        -moz-animation-duration: 1s;
        animation-duration: 1s;
    }

    .fadeIn.first {
        -webkit-animation-delay: 0.4s;
        -moz-animation-delay: 0.4s;
        animation-delay: 0.4s;
    }

    .fadeIn.second {
        -webkit-animation-delay: 0.6s;
        -moz-animation-delay: 0.6s;
        animation-delay: 0.6s;
    }

    .fadeIn.third {
        -webkit-animation-delay: 0.8s;
        -moz-animation-delay: 0.8s;
        animation-delay: 0.8s;
    }

    .fadeIn.fourth {
        -webkit-animation-delay: 1s;
        -moz-animation-delay: 1s;
        animation-delay: 1s;
    }

    /* Simple CSS3 Fade-in Animation */
    .underlineHover:after {
        display: block;
        left: 0;
        bottom: -10px;
        width: 0;
        height: 2px;
        background-color: #56baed;
        content: "";
        transition: width 0.2s;
    }

    .underlineHover:hover {
        color: #0d0d0d;
    }

    .underlineHover:hover:after {
        width: 100%;
    }

    /* OTHERS */

    *:focus {
        outline: none;
    }

    #icon {
        width: 40%;
    }
</style>

<!--<div class="wrapper fadeInDown">-->
<!--    <div id="formContent">-->
<!--        &lt;!&ndash; Tabs Titles &ndash;&gt;-->

<!--        &lt;!&ndash; Icon &ndash;&gt;-->
<!--        <div class="fadeIn first">-->
<!--            &lt;!&ndash;            <img src="../../../assets/login-icon.ico" id="icon" alt="User Icon"/>&ndash;&gt;-->
<!--        </div>-->

<!--        &lt;!&ndash; Login Form &ndash;&gt;-->
<!--        <form>-->
<!--            <label>-->
<!--                <input bind:value={usernameOrEmail} type="text" class="fadeIn second" placeholder="Username Or Email"/>-->
<!--            </label>-->
<!--            <label>-->
<!--                <input bind:value={password} type="password" class="fadeIn third" placeholder="password"/>-->
<!--            </label>-->
<!--            <button type="submit" class="fadeIn fourth"> Log In </button>-->
<!--        </form>-->

<!--        &lt;!&ndash; Admin registration &ndash;&gt;-->
<!--        <div id="formFooter">-->
<!--            <a class="underlineHover" href="AdminSignUp.svelte">Create An Account?</a>-->
<!--        </div>-->
<!--    </div>-->
<!--</div>-->

test
