import { Dispatch, FC, RefObject, useRef } from 'react';
import { useDispatch } from 'react-redux';
import { UnknownAction } from 'redux';
import { changeToSignIn, changeToSignUp } from '../../fragments/singing/formSlice';
import styles from '../../styles';
import SigningType from '../sign/SigningType';
import SigningWindow from '../sign/SigningWindow';
import { useDetectOutsideClick } from '../sign/useDetectOutsideClick';
import CTA from './CTA';
import Footer from './Footer';
import Header from './Header';
import MainHome from './MainHome';
import Motivation from './Motivation';
import Stats from './Stats';

const Home: FC = () => {
    const signInButtonRef: RefObject<HTMLLIElement> = useRef(null);
    const signUpButtonRef: RefObject<HTMLLIElement> = useRef(null);
    const signWindowRef: RefObject<HTMLDivElement> = useRef(null);
    const clickableRefs = [signWindowRef, signInButtonRef, signUpButtonRef];
    const [isActive, setIsActive] = useDetectOutsideClick(clickableRefs, false);
    const dispatch: Dispatch<UnknownAction> = useDispatch();
    const onClick = (signingType: SigningType) => {
        if (signingType === SigningType.SIGNING_IN) {
            dispatch(changeToSignIn());
        }
        if (signingType === SigningType.SIGNING_UP) {
            dispatch(changeToSignUp());
        }
        setIsActive(true);
    }

    return (
        <div className="bg-primary w-full overflow-hidden">
            <div className={` ${styles.flexCenter}`}>
                <div className="w-full px-5">
                    <Header
                        signingOpenListener={onClick}
                        singingInRef={signInButtonRef}
                        singingUpRef={signUpButtonRef}
                    />
                </div>
            </div>

            <SigningWindow
                signWindowRef={signWindowRef}
                isActive={isActive}
            />
            <div className={`bg-primary ${styles.flexStart}`}>
                <div className={`${styles.boxWidth}`}>
                    <MainHome 
                     signingOpenListener={onClick}/>
                </div>
            </div>

            <div className={`bg-primary ${styles.flexStart}`}>
                <div className={`${styles.boxWidth}`}>
                    <Stats />
                    <Motivation />
                    <CTA />
                    <Footer />
                </div>
            </div>
        </div >
    )
};

export default Home;