import { Transition } from '@headlessui/react';
import { FC, Fragment, RefObject } from 'react';
import { useSelector } from 'react-redux';
import {
    FormState
} from '../../fragments/singing/formSlice';
import SigningContent from './SigningContent';



interface Props<E extends HTMLElement> {
    signWindowRef: RefObject<E>;
    isActive: boolean;
}

const SigningWindow: FC<Props<HTMLDivElement>> = ({
    signWindowRef,
    isActive
}) => {

    return (
        <>
            <Transition
                as={Fragment}
                show={isActive}
                enter='transform transition ease-in-out duration-[250ms]'
                enterFrom='opacity-0 -translate-y-full'
                enterTo='opacity-100 translate-y-0'
                leave='transform transition ease-in-out duration-[250ms]'
                leaveFrom='opacity-100 translate-y-0'
                leaveTo='opacity-0 -translate-y-full'
            >
                <div
                    id='signing-menu'
                    ref={signWindowRef}
                    className={` 
            z-10 fixed w-max h-fit mx-auto inset-x-0 font-poppins rounded-lg
            shadow-xl md:mt-0 sm:max-w-md xl:p-0 bg-opaque-gradient
            duration-300 peer-active:
            `}>
                    <SigningContent />

                </div>
            </Transition>
        </>
    );
}

export default SigningWindow;