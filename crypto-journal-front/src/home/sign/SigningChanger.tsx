import { Transition } from '@headlessui/react';
import { Dispatch, FC, RefObject, useRef } from 'react';
import { useDispatch } from 'react-redux';
import { UnknownAction } from 'redux';
import { changeToSignIn, changeToSignUp } from '../../fragments/singing/formSlice';
import SigningFormChangeButton from './SigningFormChangeButton';
import SigningType from './SigningType';

interface Props {
    activeType: SigningType,
    signInFormFirstField: RefObject<HTMLInputElement>,
    signUpFormFirstField: RefObject<HTMLInputElement>,
}

const SigningChanging: FC<Props> = ({
    activeType,
    signInFormFirstField,
    signUpFormFirstField,
}) => {
    const dispatch: Dispatch<UnknownAction> = useDispatch();
    const signInButtonRef: RefObject<HTMLButtonElement> = useRef(null);
    const signUpButtonRef: RefObject<HTMLButtonElement> = useRef(null);
    return (
        <div className='flex flex-col w-full items-center justify-center content-center'>
            <div className={`flex flex-row bg-[rgba(17,16,29,0.8)] w-[261px] h-[46px] z-6
            justify-self-center justify-between mt-4 p-1 text-[13px] font-medium text-slate-50 rounded-2xl px-2`}>
                <Transition
                >
                    <SigningFormChangeButton
                        buttonRef={signUpButtonRef}
                        tabIndex={activeType === SigningType.SIGNING_IN ? -1 : 0}
                        clickListener={() => {
                            dispatch(changeToSignIn());
                            signInFormFirstField.current?.focus();
                        }}
                        text='Sign in'
                    />
                </Transition>
                <Transition
                >
                    <SigningFormChangeButton
                        buttonRef={signInButtonRef}
                        tabIndex={activeType === SigningType.SIGNING_UP ? -1 : 0}
                        clickListener={() => {
                            dispatch(changeToSignUp());
                            signUpFormFirstField.current?.focus();
                        }}
                        text='Sign up'
                    />
                </Transition>
            </div>
            <div className={`flex flex-row -translate-y-10 z-8 w-[261px]
            justify-self-center text-[13px] font-medium rounded-lg h-0 px-1.5`}>
                <Transition
                    show={activeType === SigningType.SIGNING_IN}
                    enter='transform transition ease-in-out duration-[250ms]'
                    enterFrom='translate-x-[166px]'
                    enterTo='translate-x-0'
                >

                    <div className=' bg-blue-gradient py-2 px-4 rounded-xl w-max'>
                        Sign in
                    </div>

                </Transition>

            </div>
            <div className={`flex flex-row -translate-y-10 z-8 w-[261px]
            justify-self-center text-[13px] font-medium rounded-lg h-0 px-1.5`}>
                <Transition

                    show={activeType === SigningType.SIGNING_UP}
                    enter='transform transition ease-in-out duration-[250ms]'
                    enterFrom='-translate-x-[166px] '
                    enterTo='translate-x-0 '
                >

                    <div className='ml-[166px] bg-blue-gradient py-2 px-4 rounded-xl w-max'>
                        Sign up
                    </div>

                </Transition>
            </div>

        </div>);
}
export default SigningChanging;